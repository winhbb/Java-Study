package com.gaodun.storm.study.common.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnterRoomParam {
    public Long sid;
    public String name;
    public Long gcid;
    private Long courseId;
    private Long syllabusId;

    public EnterRoomParam() {
    }

    public EnterRoomParam(Long sid, String name, Long gcid, Long courseId, Long syllabusId) {
        this.sid = sid;
        this.name = name;
        this.gcid = gcid;
        this.courseId = courseId;
        this.syllabusId = syllabusId;
    }
}
