package com.haohan.mvcframework.demo.controller;

import com.haohan.mvcframework.demo.service.DemoService;
import com.haohan.mvcframework.framework.annotation.HHAutowired;
import com.haohan.mvcframework.framework.annotation.HHController;
import com.haohan.mvcframework.framework.annotation.HHRequestMapping;
import com.haohan.mvcframework.framework.annotation.HHRequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author com.haohan
 * 04/15/2020 - 10:26 上午
 */
@HHController
@HHRequestMapping("/demo")
public class DemoController {

    @HHAutowired
    private DemoService demoService;

    @HHRequestMapping("/query")
    public void query(HttpServletResponse response,
                      @HHRequestParam String name) {

        String result = demoService.get(name);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
