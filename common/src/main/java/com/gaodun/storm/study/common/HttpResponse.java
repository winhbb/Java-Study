package com.gaodun.storm.study.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HttpResponse<T> implements Serializable {
    private int status;
    private String message;
    private T result;

    public HttpResponse() {
    }

    public HttpResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpResponse(int status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public HttpResponse(StatusCode statusCode, T result) {
        this.status = statusCode.code;
        this.message = statusCode.message;
        this.result = result;
    }
}
