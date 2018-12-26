package com.zynsun.openplatform.dubbo;

import java.lang.annotation.*;

/**
 * 用于描述Dubbo服务中的方法
 * Created by tanzhen on 2017/5/12.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface DubboMethod {

    /**
     * 方法描述
     *
     * @return
     */
    public String description() default "";

    /**
     * 方法别名
     *
     * @return
     */
    public String displayName() default "";

    /**
     * 返回值
     *
     * @return
     */
    public String returnParams() default "";
}
