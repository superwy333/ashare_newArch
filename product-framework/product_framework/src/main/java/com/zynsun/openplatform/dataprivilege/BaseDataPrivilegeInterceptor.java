package com.zynsun.openplatform.dataprivilege;

import com.github.pagehelper.sqlsource.PageDynamicSqlSource;
import com.zynsun.openplatform.shiro.SubjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 权限拦截器
 *
 * @author david
 * @create 2017/5/12 13:48
 */
//@Intercepts(value = {@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public abstract class BaseDataPrivilegeInterceptor implements Interceptor {

    private Set<Integer> sourceStorage = new HashSet<Integer>(); // 缓存代理对象

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();

        MappedStatement mappedStatement = (MappedStatement) args[0];
        Object parameter = args[1];

        SqlSource sqlSource = mappedStatement.getSqlSource();
        // 只拦截动态sql
        if (sqlSource instanceof PageDynamicSqlSource || sqlSource instanceof DynamicSqlSource) {
            // 获取到sqlNode对象
            Field field = sqlSource.getClass().getDeclaredField("rootSqlNode");
            field.setAccessible(true);
            SqlNode sqlNode = (SqlNode) field.get(sqlSource);
            if (!sourceStorage.contains(sqlSource.hashCode())) {
                // 获取动态代理对象
                SqlNode proxyNode = (SqlNode) Proxy.newProxyInstance(sqlNode.getClass().getClassLoader(), new Class[]{
                        SqlNode.class
                }, new SqlNodeInvocationHandler(sqlNode, mappedStatement, parameter));
                field.set(sqlSource, proxyNode);
                sourceStorage.add(sqlSource.hashCode());
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    /**
     * 核心处理方法
     */
    private class SqlNodeInvocationHandler implements InvocationHandler {
        private SqlNode target;
        private MappedStatement mappedStatement;
        private Object parameter;

        private SqlNodeInvocationHandler(SqlNode target, MappedStatement mappedStatement, Object parameter) {
            super();
            this.target = target;
            this.mappedStatement = mappedStatement;
            this.parameter = parameter;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            DynamicContext context = (DynamicContext) args[0];

            // 数据权限分类：<类型KEY, 具体值(逗号分隔)>
            Map<String, String> typeValueMap = new HashMap<String, String>();

            boolean usePermissionControl = isUsePermissionControl();

            if (usePermissionControl) { // 使用权限控制
                // 清空权限
                permissionMap.get().clear();
                // 设置权限
                settingPermission(mappedStatement, parameter, context);
            }

            // 获取已经设置的权限
            Map<String, Set<String>> permissions = permissionMap.get();

            // 将权限值设置到mybatis上下文中，方便在xxxMapper.xml文件中使用
            context.bind("DP", permissions); // 绑定上下文权限：使用方式 ${tools.setDP('company_code', 'DPT_COMPANY')}
            context.bind("useDP", usePermissionControl); // 是否使用数据权限控制：使用方式 ${userDP}
            context.bind("tools", new MybatisTools(context));
            context.bind("CURRENT_USER", SubjectUtil.getUser());

            // 调用查询方法
            return method.invoke(target, args);
        }
    }

    /**
     * 当前线程中登录用户的权限
     */
    private final static ThreadLocal<Map<String, Set<String>>> permissionMap = new ThreadLocal<Map<String, Set<String>>>() {
        @Override
        protected Map<String, Set<String>> initialValue() {
            return new ConcurrentHashMap<String, Set<String>>();
        }
    };

    /**
     * 为权限添加条件
     *
     * @param permissionType 权限类型
     * @param condition      条件
     */
    public void addPermission(String permissionType, String condition) {
        this.getPermission(permissionType).add(condition);
    }

    /**
     * 为权限设置新条件
     *
     * @param permissionType 权限类型
     * @param condition      条件
     */
    public void setPermission(String permissionType, String condition) {
        this.deletePermission(permissionType);
        this.getPermission(permissionType).add(condition);
    }

    /**
     * 获取对应的权限列表
     *
     * @param permissionType 权限类型
     * @return 对应的权限列表
     */
    public Set<String> getPermission(String permissionType) {
        Set<String> set = permissionMap.get().get(permissionType);
        if (set == null) {
            permissionMap.get().put(permissionType, new HashSet<String>());
        }
        return permissionMap.get().get(permissionType);
    }

    /**
     * 删除对应的权限
     *
     * @param permissionType 权限类型
     */
    public void deletePermission(String permissionType) {
        permissionMap.get().remove(permissionType);
    }

    /**
     * 删除指定的权限
     *
     * @param permissionType 权限类型
     * @param condition      条件
     */
    public void deletePermission(String permissionType, String condition) {
        this.getPermission(permissionType).remove(condition);
    }

    /**
     * 判断是否有对应的权限
     *
     * @param permissionType 权限类型
     * @return true: 有
     */
    public boolean hasPermission(String permissionType) {
        return permissionMap.get().containsKey(permissionType);
    }

    /**
     * 是否使用权限控制: 不受数据维度限制
     *
     * @return true: 使用。
     */
    public abstract boolean isUsePermissionControl();

    /**
     * 设置权限
     *
     * @param mappedStatement mybatis的查询器
     * @param parameter 查询使用的参数
     * @param context 查询上下文，通过bind方法可以绑定自定义变量。
     */
    public abstract void settingPermission(MappedStatement mappedStatement, Object parameter, DynamicContext context);
}
