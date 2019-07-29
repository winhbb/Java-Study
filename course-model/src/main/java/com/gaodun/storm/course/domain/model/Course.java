package com.gaodun.storm.course.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "zs_course")
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "course_type")
    private Integer courseType;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "ware_status")
    private Integer wareStatus;

    @Column(name = "allow_question")
    private Integer allowQuestion;

    @Column(name = "allow_download")
    private Integer allowDownload;

    @Column(name = "allow_plan")
    private Integer allowPlan;

    @Column(name = "allow_exam")
    private Integer allowExam;

    @Column(name = "allow_investigate")
    private Integer allowInvestigate;

    @Column(name = "investigate_url")
    private String investigateUrl;

    @Column(name = "mock_gradation_open")
    private Integer mockGradationOpen;

    @Column(name = "video_id")
    private Long videoId;

    @Column(name = "cover")
    private String cover;

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "class_type")
    private Integer classType;

    @Column(name = "created_at")
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at")
    private java.sql.Timestamp updatedAt;
}
