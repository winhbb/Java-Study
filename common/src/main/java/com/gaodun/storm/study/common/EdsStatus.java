package com.gaodun.storm.study.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdsStatus<T> {
    private int status;
    private String info;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public EdsStatus() {
    }

}
