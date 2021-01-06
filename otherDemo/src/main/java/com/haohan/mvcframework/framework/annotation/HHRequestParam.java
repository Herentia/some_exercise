package com.haohan.mvcframework.framework.annotation;

import java.lang.annotation.*;

/**
 * @author com.haohan
 * 04/15/2020 - 10:23 上午
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HHRequestParam {
    String value() default "";
}
