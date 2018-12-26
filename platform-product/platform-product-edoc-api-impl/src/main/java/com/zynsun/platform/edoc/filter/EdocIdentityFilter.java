package com.zynsun.platform.edoc.filter;

import com.alibaba.dubbo.rpc.*;
import com.zynsun.platform.edoc.EdocContext;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author zhouhong edoc身份认证filter
 * @create 2018/1/9
 * @modify
 */
public class EdocIdentityFilter implements Filter {
    private static final String TENANT_ID_KEY = "TENANT_ID"; // 当前租户ID/系统标识
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext context = RpcContext.getContext();

        //获取edoc身份信息
        String tenantId=EdocContext.getTenantId();
        if(!StringUtils.isEmpty(tenantId)){
            context.setAttachment(TENANT_ID_KEY,tenantId);
        }else{
            context.setAttachment(TENANT_ID_KEY, "EDOC"); // 默认
        }
        Assert.hasLength(context.getAttachment(TENANT_ID_KEY), "请设置当前系统标识 RpcContext.getContext().setAttachment(\"TENANT_ID\", \"xxx\")");
        EdocContext.setTenantId(context.getAttachment(TENANT_ID_KEY));
        return invoker.invoke(invocation);
    }
}
