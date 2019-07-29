package com.gaodun.storm.study.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Status<T> implements Serializable {
    private int status;
    private String message;
    private T result;

    public Status() {
    }

    public Status(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Status(int status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
