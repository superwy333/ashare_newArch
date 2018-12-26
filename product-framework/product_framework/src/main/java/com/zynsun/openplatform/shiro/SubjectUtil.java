package com.zynsun.openplatform.shiro;

import com.alibaba.dubbo.rpc.RpcContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zynsun.openplatform.context.PlatformContext;
import com.zynsun.openplatform.exception.BusinessException;
import org.springframework.util.Assert;

/**
 * Shiro获取当前登录用户信息
 */
public class SubjectUtil {
    /**
     * 当前用户KEY
     */
    public static final String CURRENT_USER_KEY = "_CURRENT_USER_";

    public static LoginUser getUser() {
        try {
            // 获取上下文中的当前用户信息
            LoginUser loginUser = (LoginUser) PlatformContext.getVariable(CURRENT_USER_KEY);
            if (loginUser != null) {
                return loginUser;
            } else {
                loginUser = new LoginUser();
                loginUser.setUserId(0L);
            }
            return loginUser;
        } catch (Exception e) {
            throw new BusinessException("获取当前用户信息失败：" + e.getMessage());
        }
    }

    public static void setUser(LoginUser loginUser){
        try {
            PlatformContext.setVariable(CURRENT_USER_KEY, loginUser);
        } catch (Exception e) {
            throw new BusinessException("设置当前用户信息失败：" + e.getMessage());
        }
    }
}
