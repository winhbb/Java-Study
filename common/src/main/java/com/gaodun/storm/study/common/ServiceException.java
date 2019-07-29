package com.gaodun.storm.study.common;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ServiceException extends RuntimeException {

    protected HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    private int errorCode;
    private String detail;

    public ServiceException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    // 错误码默认1失败
    public ServiceException(String message) {
        super(message);
        this.errorCode = 1;
    }

    public ServiceException(StatusCode statusCode, String detail) {
        super(statusCode.message);
        this.errorCode = statusCode.code;
        this.detail = detail;
    }
}