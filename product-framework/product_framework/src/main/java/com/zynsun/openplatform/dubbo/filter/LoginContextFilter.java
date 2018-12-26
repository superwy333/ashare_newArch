package com.zynsun.openplatform.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zynsun.openplatform.context.PlatformContext;
import com.zynsun.openplatform.shiro.SubjectUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 登录用户信息过滤器
 * @author david
 * @create 2017/5/17 21:40
 */
public class LoginContextFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginContextFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        try {
            PlatformContext.setVariable(SubjectUtil.CURRENT_USER_KEY, null);

            Subject subject = SecurityUtils.getSubject();
            LOGGER.debug("[用户跟踪service] {}, {}", PlatformContext.getVariables(), subject);
            PlatformContext.setVariable(SubjectUtil.CURRENT_USER_KEY, null);
            if(subject != null){
                Object principal = subject.getPrincipal();
                LOGGER.debug("[用户跟踪service] {}, {}", principal);
                if(principal != null){ // 已登录
                    PlatformContext.setVariable(SubjectUtil.CURRENT_USER_KEY, principal);
                }
            }
        } catch (Exception e) {
            throw new RpcException(e);
        }
        return invoker.invoke(invocation);
    }
}
