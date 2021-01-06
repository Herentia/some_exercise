package com.pb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author haohan
 * @date 2020/9/3_13:44
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!!!";
    }

}
