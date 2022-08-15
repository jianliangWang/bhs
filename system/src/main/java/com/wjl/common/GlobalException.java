package com.wjl.common;

import io.jsonwebtoken.JwtException;
import java.nio.file.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    Logger logger = LoggerFactory.getLogger(GlobalException.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultJson handler(IllegalArgumentException e) {
        logger.error("Assert异常：---------{}", e.getMessage());
        return ResultJson.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RuntimeException.class)
    public ResultJson handler(RuntimeException e) {
        logger.error("运行时异常：---------{}", e.getMessage());
        return ResultJson.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResultJson handler(AccessDeniedException e) {
        logger.error("security权限不足：-------{}", e.getMessage());
        return ResultJson.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultJson handler(MethodArgumentNotValidException e) {
        logger.error("实体校验异常：---------{}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return ResultJson.fail(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(JwtException.class)
    public ResultJson handler(JwtException e) {
        logger.error("token异常：------{}", e.getMessage());
        return ResultJson.fail(e.getMessage());
    }
}
