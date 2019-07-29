package com.gaodun.storm.study.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StudentInfo implements Serializable {
    @JsonProperty("member_id")
    private String memberId;

    @JsonProperty("student_id")
    private String studentId;

    @JsonProperty("real_name")
    private String realName;

    @JsonProperty("nickname")
    private String nickName;
}
