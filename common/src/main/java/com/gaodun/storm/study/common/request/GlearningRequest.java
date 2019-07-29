package com.gaodun.storm.study.common.request;

import com.gaodun.storm.study.common.Exceptions;
import com.gaodun.storm.study.common.GlearningStatus;
import com.gaodun.storm.study.common.ServiceException;
import com.gaodun.storm.study.common.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GlearningRequest {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.profiles.active}")
    private String envActive;

    private static final Long TEST_STUDENT = 11223719L;
    private static final String PRODUCTION = "production";

    /**
     * 进班
     *
     * @param gcid
     * @param sid
     * @param name
     * @return
     */
    public void enterGlearningClass(Long gcid, Long sid, String name) {
        if (gcid < 1 || sid < 1) {
            throw new ServiceException("班级或者学生id不能为空");
        }
        Map<String, Object> uriParams = new HashMap<>();
        if (!PRODUCTION.equals(envActive)) {
            sid = TEST_STUDENT;
        }

        String gurl = String.format("http://%s/class/gaodunStudent/add?class=%d&gaodunStudentID=%d&name=%s",
                Domain.GLEARNING_API, gcid, sid, name);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(gurl);
        ResponseEntity<GlearningStatus> response = restTemplate.exchange(builder.buildAndExpand(uriParams).toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<GlearningStatus>() {
        });
        if (!response.hasBody()) {
            throw new ServiceException(String.format(Exceptions.EMPTY_BODY_ERROR_FORMAT, gurl));
        }
        GlearningStatus result = response.getBody();
        if (result.getStatus() != 0) {
            throw new ServiceException(String.format(Exceptions.API_ERROR_FORMAT, gurl, result.getInfo()));
        }

        return;
    }

    /**
     * 退glearning班
     *
     * @param gcid
     * @param sid
     * @return
     */
    public void deleteGlearningClass(Long gcid, Long sid) {
        if (gcid < 1 || sid < 1) {
            throw new ServiceException("班级或者学生id不能为空");
        }
        if (!PRODUCTION.equals(envActive)) {
            sid = TEST_STUDENT;
        }
        Map<String, Object> uriParams = new HashMap<>();
        String gurl = String.format("http://%s/class/gaodunStudent/delete?class=%d&gaodunStudentID=%d", Domain.GLEARNING_API, gcid, sid);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(gurl);

        ResponseEntity<GlearningStatus> response = restTemplate.exchange(builder.buildAndExpand(uriParams).toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<GlearningStatus>() {
        });
        if (!response.hasBody()) {
            throw new ServiceException(String.format(Exceptions.EMPTY_BODY_ERROR_FORMAT, gurl));
        }
        GlearningStatus result = response.getBody();
        if (result.getStatus() != 0) {
            throw new ServiceException(String.format(Exceptions.API_ERROR_FORMAT, gurl, result.getInfo()));
        }
        return;
    }

    public Map<String, Object> studentsDeleteGlearningClass(Long gcid, List<Long> studentids) {
        Map<String, Object> res = new HashMap<>();
        List<Long> errNum = new ArrayList<>();
        int n = studentids.size();
        for (int i = 0; i < n; i++) {
            Long sid = studentids.get(i);
            try {
                this.deleteGlearningClass(gcid, sid);
            } catch (Exception e) {
//                e.printStackTrace();
                errNum.add(sid);
            }
        }
        res.put("all_num", n);
        res.put("err_student_id", errNum);
        return res;
    }

    /*
     * 学员批量入班
     */
    public EnterRoomStudent studentsEnterGlearningClass(EnterRoomParam[] students) {
        List<EnterRoomParam> failStudents = new ArrayList<>();

        for (int i = 0; i < students.length; i++) {
            EnterRoomParam oneStudent = students[i];
            try {
                this.enterGlearningClass(oneStudent.getGcid(), oneStudent.getSid(), oneStudent.getName());
            } catch (Exception e) {
                failStudents.add(oneStudent);
            }
        }
        return new EnterRoomStudent(students.length, failStudents);
    }

    /*
     * 学员退班
     */
    public EnterRoomStudent studentOutGlearningClass(EnterRoomParam[] students) {
        List<EnterRoomParam> failStudents = new ArrayList<>();

        for (int i = 0; i < students.length; i++) {
            EnterRoomParam oneStudent = students[i];
            try {
                this.deleteGlearningClass(oneStudent.getGcid(), oneStudent.getSid());
            } catch (Exception e) {
                failStudents.add(oneStudent);
            }
        }
        return new EnterRoomStudent(students.length, failStudents);
    }
}

