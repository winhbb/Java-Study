package com.gaodun.storm.study.common;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    public HttpResponse handleUnProcessableException(Exception e, HttpServletResponse response) {
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            for (ObjectError error : methodArgumentNotValidException.getBindingResult().getAllErrors()) {
                return new HttpResponse(StatusCode.BAD_PARAMETER.code, String.format("%s:%s", StatusCode.BAD_PARAMETER.message,error.getDefaultMessage()));
            }
            return new HttpResponse(StatusCode.BAD_PARAMETER, e.getMessage());
        }
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            return new HttpResponse(serviceException.getErrorCode(), e.getMessage());
        }
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new HttpResponse(1, "接口异常，请联系管理员！", e.getMessage());
    }
}
