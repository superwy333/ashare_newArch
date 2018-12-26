package com.zynsun.openplatform.dataprivilege;

import com.zynsun.openplatform.shiro.DataPrivilege;
import com.zynsun.openplatform.shiro.SubjectUtil;
import com.zynsun.openplatform.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * mybatis权限拦截器
 *
 * @author david
 * @created 2016/11/11 13:54
 */
@Intercepts(value = {@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class DefaultDataPrivilegeInterceptor extends BaseDataPrivilegeInterceptor {

    /**
     * 是否使用权限控制: 不受数据维度限制
     *
     * @return true: 使用。
     */
    @Override
    public boolean isUsePermissionControl() {
        // 非超级用户、非供应商
        return SubjectUtil.getUser() != null && !SubjectUtil.getUser().isSuperUser() && SubjectUtil.getUser().getOrgCode() == null
                && SubjectUtil.getUser().getUserId() != 0;
    }

    /**
     * 设置权限
     *
     * @param mappedStatement mybatis的查询器
     * @param parameter 查询使用的参数
     * @param context 查询上下文，通过bind方法可以绑定自定义变量。
     */
    public void settingPermission(MappedStatement mappedStatement, Object parameter, DynamicContext context) {
        List<DataPrivilege> dataPrivilegeList = SubjectUtil.getUser().getDataPrivilegeList();

        for (DataPrivilege dataPrivilege : dataPrivilegeList){
            //龚道文 维度代码 维度代码强制不能为空
            /*if(StringUtils.isNotBlank(dataPrivilege.getPrivilegeValue())) {
                String[] permissions = dataPrivilege.getPrivilegeValue().split(",");
                for (String permission : permissions) {
                    if (StringUtils.isNotBlank(permission) && StringUtils.isNotBlank(dataPrivilege.getPrivilegeType())) { // 如果值为空，则不做处理
                        this.addPermission(dataPrivilege.getPrivilegeType(), permission.trim());
                    }
                }
            }*/
            //添加有维度代码为空的维度，允许维度代码为空条件，配置给运维查看操作权限
            String[] permissions = dataPrivilege.getPrivilegeValue().split(",");
            for (String permission : permissions) {
                if (StringUtils.isNotBlank(dataPrivilege.getPrivilegeType())) { // 如果值为空，则不做处理
                    this.addPermission(dataPrivilege.getPrivilegeType(), permission.trim());
                }
            }
        }
    }
}
