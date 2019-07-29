package com.gaodun.storm.study.common.request;

import java.io.Serializable;

public class CourseProgress implements Serializable {
    private Integer allChapter;
    private Integer finishedChapter;
    private Integer runChapter;
    private String courseId;
    private String studentId;
    private String currentStudySubject;
    private String courseType;
    private Double progress;
    private Long lastLeaningTime;
    private String currentStudyUrl;

    public Integer getAllChapter() {
        return allChapter;
    }

    public void setAllChapter(Integer allChapter) {
        this.allChapter = allChapter;
    }

    public Integer getFinishedChapter() {
        return finishedChapter;
    }

    public void setFinishedChapter(Integer finishedChapter) {
        this.finishedChapter = finishedChapter;
    }

    public Integer getRunChapter() {
        return runChapter;
    }

    public void setRunChapter(Integer runChapter) {
        this.runChapter = runChapter;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCurrentStudySubject() {
        return currentStudySubject;
    }

    public void setCurrentStudySubject(String currentStudySubject) {
        this.currentStudySubject = currentStudySubject;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Long getLastLeaningTime() {
        return lastLeaningTime;
    }

    public void setLastLeaningTime(Long lastLeaningTime) {
        this.lastLeaningTime = lastLeaningTime;
    }

    public String getCurrentStudyUrl() {
        return currentStudyUrl;
    }

    public void setCurrentStudyUrl(String currentStudyUrl) {
        this.currentStudyUrl = currentStudyUrl;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
