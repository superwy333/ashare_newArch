package com.zynsun.openplatform.cache;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cache {

    /**
     * 缓存名称（可以用于分组）。默认default
     */
    String name() default "default";

    /**
     * 缓存的KEY名称
     */
    String key();

    /**
     * 失效时间（单位：s）。默认：1天
     */
    long expire() default 24 * 3600L;

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @Documented
    public @interface List {
        Cache[] value();
    }
}
