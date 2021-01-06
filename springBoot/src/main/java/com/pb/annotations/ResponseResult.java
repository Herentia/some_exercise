package com.pb.annotations;

import java.lang.annotation.*;

/**
 * @author haohan
 * @date 2021/1/6 15:05
 * 用来标记方法返回值，是否需要包装
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
