package com.gaodun.storm.study.common.request.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LearningResponse<T> {
    @JsonProperty("application_name")
    private String applicationName;
    private String message;
    private Integer status;
    private T data;
}
