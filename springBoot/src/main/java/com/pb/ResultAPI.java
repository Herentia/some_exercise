package com.pb;

import com.pb.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author haohan
 * @date 2021/1/6 14:21
 * 返回结果
 */
@Data
public class ResultAPI implements Serializable {

    private Integer code;
    private String message;
    private Object data;

    public ResultAPI(ResultCode resultCode, Object data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

}
