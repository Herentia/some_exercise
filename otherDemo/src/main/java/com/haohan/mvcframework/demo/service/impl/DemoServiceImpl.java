package com.haohan.mvcframework.demo.service.impl;

import com.haohan.mvcframework.demo.service.DemoService;
import com.haohan.mvcframework.framework.annotation.HHService;

/**
 * @author com.haohan
 * 04/15/2020 - 10:31 上午
 */
@HHService
public class DemoServiceImpl implements DemoService {
    public String get(String name) {
        return "哈哈===>>>" + name;
    }
}
