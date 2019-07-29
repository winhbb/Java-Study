package com.gaodun.storm.study.common.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnterRoomStudent {
    private Integer total;
    private List<EnterRoomParam> failStudent;

    public EnterRoomStudent() {

    }

    public EnterRoomStudent(Integer total, List<EnterRoomParam> failStudent) {
        this.total = total;
        this.failStudent = failStudent;
    }
}
