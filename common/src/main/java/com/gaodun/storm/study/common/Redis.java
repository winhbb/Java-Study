package com.gaodun.storm.study.common;

public class Redis {
    private static final String KEY_PREFIX = "studyj_";
    public static final String PREFIX_PROGRESS = KEY_PREFIX + "progress_studentid_%d_courseid_%d";
    public static final String PREFIX_PROGRESS_PATTERN = KEY_PREFIX + "progress_studentid_*_courseid_%d";
    public static final String PREFIX_ALL_PROGRESS_PATTERN = KEY_PREFIX + "progress_studentid_*_courseid_*";
    public static final String STUDENT_COURSE_PROGRESS_SAAS = "student_%d_course_progress_saas";
}
