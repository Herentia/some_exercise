package com.haohan.mvcframework.framework.annotation;

import java.lang.annotation.*;

/**
 * @author com.haohan
 * 04/15/2020 - 10:22 上午
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HHAutowired {
    String value() default "";
}
