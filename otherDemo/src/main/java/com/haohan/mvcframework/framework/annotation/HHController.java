package com.haohan.mvcframework.framework.annotation;

import java.lang.annotation.*;

/**
 * @author com.haohan
 * 04/15/2020 - 10:18 上午
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HHController {

    String value() default "";

}
