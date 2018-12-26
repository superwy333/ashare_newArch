package com.zynsun.openplatform.dataprivilege;

import com.zynsun.openplatform.util.BeanUtil;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.scripting.xmltags.DynamicContext;

import java.util.*;

/**
 * Mybatis数据权限工具类
 *
 * @author david
 * @create 2017/5/18 14:58
 */
public class MybatisTools {

    private DynamicContext context;

    public MybatisTools(DynamicContext context) {
        this.context = context;
    }

    /**
     * 设置数据维度
     *
     * @param columnName        列名。如果有别名，需要带上
     * @param dataPrivilegeType 数据维度类型代码
     * @return sql文本
     */
    @SuppressWarnings("unchecked")
    public String setDP(String columnName, String dataPrivilegeType) {
        Map<String, Object> bindings = context.getBindings();

        // 是否使用数维度
        Boolean userDP = (Boolean) bindings.get("useDP");
        if (Boolean.TRUE.equals(userDP)) {
            // 获取数据维度对象
            Map<String, Set<String>> dp = (Map<String, Set<String>>) bindings.get("DP");
            // 获取对应的权限信息
            Set<String> permissions = dp.get(dataPrivilegeType);
            if (!BeanUtil.isEmpty(permissions)) {
                List<String> list = new ArrayList<>();
                for (String permission : permissions) {
                    list.add(String.format("%s = '%s'", columnName, permission));
                }
                return "(" + StringUtils.join(list, " or ") + ")";
            } else {
                return " 1 = 0 ";
            }
        }
        return " 1 = 1 ";
    }

    /**
     * 设置条件
     *
     * @param columnName 列名。如果有别名，需要带上
     * @param condition  条件
     * @return sql文本
     */
    @SuppressWarnings("unchecked")
    public String setCondition(String columnName, Object condition) {
        Map<String, Object> bindings = context.getBindings();

        // 是否使用数维度
        Boolean userDP = (Boolean) bindings.get("useDP");
        if (Boolean.TRUE.equals(userDP)) {
            if (!BeanUtil.isEmpty(condition)) {
                if (condition instanceof Collection<?>) {
                    Object[] list = ((Collection<?>) condition).toArray();
                    return String.format(" %s in ('%s') ", columnName, StringUtils.join(escapeSqlForArray(list), "','"));
                }
                if (condition instanceof Object[]) {
                    Object[] list = (Object[]) condition;
                    return String.format(" %s in ('%s') ", columnName, StringUtils.join(escapeSqlForArray(list), "','"));
                }
                return String.format(" %s = '%s' ", columnName, escapeSql(condition));
            } else {
                return " 1 = 0 ";
            }
        }
        return " 1 = 1 ";
    }

    /**
     * 根据list设置条件
     * @param columnName
     * @param value
     * @return
     */
    public String list(String columnName, Object value){
        List<Object> objectList = toObjectList(value);
        List<String> strings = new ArrayList<>();
        if (!BeanUtil.isEmpty(value)) {
            for (Object obj : objectList){
                if(obj == null || obj.toString().equals("-1")){
                    strings.add(escapeSql("(" + columnName + " is null)"));
                } else {
                    strings.add(escapeSql(columnName + " = " + obj));
                }
            }
            return StringUtils.join(strings, " or ");
        } else {
            return "";
        }
    }

    /**
     * 过滤SQl字符
     *
     * @param obj 值
     * @return 转换过的字符串
     */
    public String escapeSql(Object obj) {
        if (obj == null) {
            return null;
        }
        return StringUtils.replace(obj.toString(), "'", "''");
    }

    /**
     * 过滤SQl字符
     *
     * @param objs 值（数组）
     * @return 转换过的字符串
     */
    public String[] escapeSqlForArray(Object[] objs) {
        String[] strs = new String[objs.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = escapeSql(objs[i]);
        }
        return strs;
    }

    private List<Object> toObjectList(Object value){
        List<Object> objectList = new ArrayList<>();
        if (value instanceof Collection<?>) {
            objectList.addAll((Collection<?>) value);
        } else if (value instanceof Object[]) {
            objectList.addAll(Arrays.asList((Object[]) value));
        } else {
            objectList.add(value);
        }
        return objectList;
    }
}
