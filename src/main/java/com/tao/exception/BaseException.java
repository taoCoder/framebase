package com.tao.exception;

import com.tao.enums.ErrorEnum;

/**
 * @author huangtao54
 * @description 异常
 * @date 2018/10/18
 */
public class BaseException extends RuntimeException{
    private int errCode;

    private String errMessage;

    public BaseException(int errCode, String errMessage) {
        super(errMessage);
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public BaseException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.errCode = errorEnum.getCode();
        this.errMessage = errorEnum.getMessage();
    }


    @Override
    public String getMessage() {
        return this.errMessage;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
