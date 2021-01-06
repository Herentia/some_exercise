package com.pb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haohan
 * @date 2020/9/3 17:18
 */
@RestController
public class AsyncController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }

}
