package com.haohan.mvcframework.framework.annotation;

import java.lang.annotation.*;

/**
 * @author com.haohan
 * 04/15/2020 - 10:21 上午
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HHService {
    String value() default "";
}
