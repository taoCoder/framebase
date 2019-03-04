package com.tao.common;

import com.tao.enums.ErrorEnum;

/**
 * @author huangtao54
 * @description 接口返回值
 * @date 2018/10/18
 */
public class Result<T> {
    private boolean success;

    private int code;

    private String message;

    private T data;

    public Result(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean success, ErrorEnum errorEnum, T data) {
        this.success = success;
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
        this.data = data;
    }

    public static <T> Result ok(T data) {
        return new Result(true, ErrorEnum.SUCCESS, data);
    }

    public static Result failed(ErrorEnum errorEnum) {
        return new Result(false, errorEnum, null);
    }

    public static Result failed(int errCode, String errMsg) {
        return new Result(false, errCode, errMsg, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
