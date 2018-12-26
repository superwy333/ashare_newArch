package com.zynsun.openplatform.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Web端获取当前登陆用户名
 */
public class WebSubjectUtil {

    public static LoginUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        LoginUser principal = (LoginUser) subject.getPrincipal();
        if (principal != null) {
            return principal;
        } else {
            principal = new LoginUser();
            principal.setUserId(0L);
        }
        return principal;

    }

    public static boolean isLogin(){
        return getUser() != null && getUser().getUserId() != null && getUser().getUserId() > 0;
    }
}
