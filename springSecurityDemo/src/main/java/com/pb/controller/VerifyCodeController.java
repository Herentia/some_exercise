package com.pb.controller;

import com.pb.utils.VerifyCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author haohan
 * @date 2020/9/3 15:57
 * 生产图片验证码
 */
@RestController
public class VerifyCodeController {

    @GetMapping("/vercode")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage image = verifyCode.getImage();
        String text = verifyCode.getText();
        request.getSession().setAttribute("index_code", text);
        VerifyCode.output(image, response.getOutputStream());
    }

}
