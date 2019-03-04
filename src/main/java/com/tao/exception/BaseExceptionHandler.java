package com.tao.exception;

import com.tao.common.Result;
import com.tao.enums.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author huangtao54
 * @description 统一异常处理
 * @date 2018/10/18
 */
@RestControllerAdvice
public class BaseExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public Result handleBaseException(BaseException e) {
        return Result.failed(e.getErrCode(), e.getErrMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常",e);
        return Result.failed(ErrorEnum.SYSTEM_UNKNOWN);
    }
}
