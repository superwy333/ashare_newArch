package com.zynsun.openplatform.dubboRestLoginFilter.annotation;

import java.lang.annotation.*;

/**
 * @description: 登录校验
 * @author: Wy
 * @create: 2018-11-19 09:39
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
