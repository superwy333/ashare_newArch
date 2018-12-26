package com.zynsun.openplatform.dubbo;

import java.lang.annotation.*;

/**
 * Created by tanzhen on 2017/5/12.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.PARAMETER)
public @interface DubboParam {

    /**
     * 参数名称
     *
     * @return
     */
    public String name() default "";

    /**
     * 参数描述
     *
     * @return
     */
    public String description() default "";

    /**
     * 参数示例
     *
     * @return
     */
    public String example() default "";

    /**
     * 是否必填(默认false)
     *
     * @return
     */
    public boolean requird() default true;
}
