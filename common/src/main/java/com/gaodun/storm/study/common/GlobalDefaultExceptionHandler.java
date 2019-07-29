package com.gaodun.storm.study.common;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Status handleUnProcessableException(Exception e, HttpServletResponse response) {
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            for (ObjectError s : methodArgumentNotValidException.getBindingResult().getAllErrors()) {
                return new Status(1, s.getObjectName() + ": " + s.getDefaultMessage());
            }
            return new Status(1, "未知参数错误");
        }
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            return new Status(serviceException.getErrorCode(), e.getMessage(), serviceException.getDetail());
        }
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new Status(1, "接口异常，请联系管理员！", e.getMessage());
    }
}
