package com.haohan.mvcframework.framework.annotation;

import java.lang.annotation.*;

/**
 * @author haohan
 * 04/15/2020 - 10:27 上午
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HHRequestMapping {
    String value() default "";
}
