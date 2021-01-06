package com.pb.controller;

import com.pb.ResultAPI1;
import com.pb.annotations.ResponseResult;
import com.pb.pojo.Stu;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author haohan
 * @date 2021/1/6 11:24
 */
@RestController
@ResponseResult
public class StuController {

    @GetMapping("/stu/{id}")
    public String getStu(@PathVariable Long id) {
        String s = null;
        String str = Optional.ofNullable(s).orElse("emrty stu data");
        return str;
    }

    @GetMapping("/stu/result")
    public ResultAPI1 getResult() {
        Stu stu = new Stu(1L, "xiao", 18);
        return ResultAPI1.success();
    }

    @GetMapping("/stu")
    public Stu getStu() {
        return new Stu(2L, "da", 19);
    }

}
