package com.pb.interceptors;

import com.pb.annotations.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author haohan
 * @date 2021/1/6 15:08
 * 请求拦截器
 */
@Slf4j
@Component
public class ResponseResultInterceptor implements HandlerInterceptor {
    //标记名称
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求方法
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            //判断是否在类对象上面加了注解
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                //设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                //设置此请求返回体，需要包装，往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
