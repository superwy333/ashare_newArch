package com.zynsun.openplatform.dubbo;

import java.lang.annotation.*;

/**
 * 用于描述Dubbo服务Service接口
 * Created by tanzhen on 2017/5/12.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface DubboService {

    /**
     * 接口说明
     *
     * @return
     */
    public String description() default "";

    /**
     * 服务接口别名
     *
     * @return
     */
    public String displayName() default "";

    /**
     * 是否对外服务接口
     *
     * @return
     */
    public boolean isExternal() default false;
}
