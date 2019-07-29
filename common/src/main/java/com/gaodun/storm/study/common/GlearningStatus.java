package com.gaodun.storm.study.common;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlearningStatus {
    private int status;
    private String info;
    private String token;
//    private T result;

    public GlearningStatus() {
    }
}

