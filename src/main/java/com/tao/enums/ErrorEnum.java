package com.tao.enums;

/**
 * @author huangtao8
 * 错误枚举
 */
public enum ErrorEnum {
    SUCCESS(0,"成功"),
    NOT_LOGIN(10000,"未登录");

    private int code;

    private String message;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
