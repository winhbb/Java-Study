package com.gaodun.storm.study.common;

public enum StatusCode {
    SUCCESS(0, "请求成功"),
    REQUEST_FAILED(1, "接口请求失败"),
    BAD_PARAMETER(2,"错误参数");
    public int code;
    public String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}