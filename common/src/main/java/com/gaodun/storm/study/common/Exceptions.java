package com.gaodun.storm.study.common;

public class Exceptions {
    /**
     * 参数为请求接口的URL
     */
    public static final String EMPTY_BODY_ERROR_FORMAT = "请求[%s]body为空";
    /**
     * 第一个参数为URL，第二个参数为失败原因
     */
    public static final String API_ERROR_FORMAT = "请求[%s]失败:%s";
}
