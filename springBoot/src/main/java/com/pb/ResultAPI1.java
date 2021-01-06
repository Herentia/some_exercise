package com.pb;

import com.pb.enums.ResultCode;
import lombok.Data;

/**
 * @author haohan
 * @date 2021/1/6 14:48
 */
@Data
public class ResultAPI1 {

    private Integer code;
    private String message;
    private Object data;

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    //返回成功_不带数据
    public static ResultAPI1 success() {
        ResultAPI1 api1 = new ResultAPI1();
        api1.setResultCode(ResultCode.SUCCESS);
        return api1;
    }

    //返回成功
    public static ResultAPI1 success(Object data) {
        ResultAPI1 api1 = new ResultAPI1();
        api1.setResultCode(ResultCode.SUCCESS);
        api1.setData(data);
        return api1;
    }

    //返回失败
    public static ResultAPI1 failure(ResultCode resultCode) {
        ResultAPI1 api1 = new ResultAPI1();
        api1.setResultCode(resultCode);
        return api1;
    }

    //返回失败
    public static ResultAPI1 failure(ResultCode resultCode, Object data) {
        ResultAPI1 api1 = new ResultAPI1();
        api1.setResultCode(resultCode);
        api1.setData(data);
        return api1;
    }

}
