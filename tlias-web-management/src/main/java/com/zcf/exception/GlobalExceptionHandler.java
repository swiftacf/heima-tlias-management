package com.zcf.exception;


import com.zcf.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
* 全局异常处理器
* */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error("出现异常：{}",e.getMessage());
        return Result.error("对不起，操作失败，请联系管理员~");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("出现异常：{}",e.getMessage());
        String message = e.getMessage();
        int i=message.indexOf("Duplicate entry");
        String errMsg=message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2]+"已存在");
    }

    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e){
        log.error("业务异常：{}",e.getMessage());
        return Result.error(e.getMessage());
    }
}
