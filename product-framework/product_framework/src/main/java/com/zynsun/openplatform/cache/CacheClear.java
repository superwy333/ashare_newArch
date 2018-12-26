package com.zynsun.openplatform.cache;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheClear {
    /**
     * 缓存名称（可以用于分组）。默认default
     */
    String name() default "default";

    /**
     * 缓存的KEY名称
     */
    String key();

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @Documented
    public @interface List {
        CacheClear[] value();
    }
}
