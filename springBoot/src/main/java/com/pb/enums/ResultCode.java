package com.pb.enums;

/**
 * @author haohan
 * @date 2021/1/6 14:14
 * 状态码枚举
 */
public enum ResultCode {
    SUCCESS(1, "成功"),
    /** 参数错误：1001-1999 */
    PARAM_IS_INVALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),

    /** 用户错误：2001-2999 */
    USER_NOT_LOGGED_IN(2001, "用户未登录，访问的路径需要验证，请登录"),
    USER_LOGIN_ERROR(2002, "账号不存在或密码错误"),
    ;

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }
    public String message() {
        return this.message;
    }
}
