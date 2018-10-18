package com.tao.exception;

import com.tao.domain.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author huangtao54
 * @description 统一异常处理
 * @date 2018/10/18
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public Result handleBaseException(BaseException e) {
        return Result.failed(e.getErrCode(), e.getErrMessage());
    }
}
