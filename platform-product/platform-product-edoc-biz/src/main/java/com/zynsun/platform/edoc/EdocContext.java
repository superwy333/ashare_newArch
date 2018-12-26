package com.zynsun.platform.edoc;

/**
 * @author zhouhong
 * @create 2018/1/9
 * @modify
 */
public class EdocContext {
    /**
     * 当前租户ID/系统标识
     */
    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<String>();

    public static String getTenantId() {
        return TENANT_ID.get();
    }

    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }
}
