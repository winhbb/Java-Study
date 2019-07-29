package com.gaodun.storm.study.common.request;

import com.gaodun.storm.study.common.Exceptions;
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

import java.util.HashMap;
import java.util.Map;

@Component
public class StudyRequest {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${domain.prefix}")
    private String prefix;

    public CourseProgress[] getProgress(Long studentId, Long[] courseIds) {
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("student_id", studentId);
        String URL = String.format("http://%s%s/student/{student_id}/course/progress/origin", prefix, Domain.STUDYAPI);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
        for (Long courseId : courseIds) {
            builder = builder.queryParam("course_id_list[]", courseId);
        }
        return getProgress(builder,uriParams,URL);
    }

    public CourseProgress[] getProgress(Long[] studentIds, Long courseId) {
        Map<String, Object> uriParams = new HashMap<>();
        uriParams.put("course_id", courseId);
        String URL = String.format("http://%s%s/course/{course_id}/students/progress/origin", prefix, Domain.STUDYAPI);
//        String URL="http://localhost/study-service.gaodun.com/index.php/course/{course_id}/students/progress/origin";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
        for (Long studentId : studentIds) {
            builder = builder.queryParam("student_id_list[]", studentId);
        }
        return getProgress(builder,uriParams,URL);
    }

    public CourseProgress[] getProgress(UriComponentsBuilder builder,Map<String, Object> uriParams,String url){
        //调用进度计算接口
        ResponseEntity<Status<CourseProgress[]>> response = restTemplate.exchange(builder.buildAndExpand(uriParams).toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<Status<CourseProgress[]>>() {
        });
        if (!response.hasBody()) {
            throw new ServiceException(String.format(Exceptions.EMPTY_BODY_ERROR_FORMAT, url));
        }
        Status<CourseProgress[]> result = response.getBody();
        if (result.getStatus() != 0) {
            throw new ServiceException(String.format(Exceptions.API_ERROR_FORMAT, url, result.getMessage()));
        }
        return result.getResult();
    }
}
