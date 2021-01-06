package com.pb;

import com.pb.annotations.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haohan
 * @date 2021/1/6 15:18
 * 重写返回体
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    //标记名称
    public static final String RESPONSE_RESULT_ANN = "RESPONSE-RESULT-ANN";

    //是否请求包含了包装注解标记，没有就直接返回，不需要重写返回体
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        //判断请求是否有包装标记
        ResponseResult responseResultAnn = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResultAnn != null;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("进入 返回体 重写格式 处理中>>>");
        return ResultAPI1.success(o);
    }
}
