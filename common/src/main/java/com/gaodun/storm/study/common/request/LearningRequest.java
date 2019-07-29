package com.gaodun.storm.study.common.request;

import com.gaodun.storm.study.common.request.apis.LearningApi;
import com.gaodun.storm.study.common.request.response.LearningResponse;
import com.gaodun.storm.study.common.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class LearningRequest {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${domain.prefix}")
    private String prefix;

    public void onStudyProgressCreated(Long studentId, Long courseId) {
        String URL = String.format("http://%s%s%s", prefix, Domain.LEARNING, LearningApi.STUDY_PROGRESS_CREATED);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
        builder = builder.queryParam("student_id", studentId).queryParam("course_id", courseId);
        ResponseEntity<LearningResponse<Object>> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<LearningResponse<Object>>() {
        });
//        ResponseUtils.handleDetailedResponseException(URL, response);
    }

    public void onOfflineCreated(Long studentId, Long[] courseIds) {
        String URL = String.format("http://%s%s%s", prefix, Domain.LEARNING, LearningApi.OFFLINE_PROGRESS_CREATED);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("student_id", studentId);
        map.add("course_ids", Arrays.stream(courseIds).filter(Objects::nonNull).map(o -> o.toString()).collect(Collectors.joining(",")));
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(map, headers);
        ResponseEntity<LearningResponse<Object>> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, requestEntity, new ParameterizedTypeReference<LearningResponse<Object>>() {
        });
//        ResponseUtils.handleDetailedResponseException(URL, response);
    }

    public void onChangeProgressInCourseId(Long[] courseIds) {
        String URL = String.format("http://%s%s%s", prefix, Domain.LEARNING, LearningApi.CHANGE_PROGRESS_IN_COURSE_ID);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
        builder = builder.queryParam("course_ids", Arrays.stream(courseIds).filter(Objects::nonNull).map(o -> o.toString()).collect(Collectors.joining(",")));
        ResponseEntity<LearningResponse<Object>> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<LearningResponse<Object>>() {
        });
//        ResponseUtils.handleDetailedResponseException(URL, response);
    }

    public void onChangeProgressCourseIdInStudentId(Long[] studentIds, Long courseId) {
        String URL = String.format("http://%s%s%s", prefix, Domain.LEARNING, LearningApi.CHANGE_PROGRESS_COURSE_ID_IN_STUDENT_ID);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("student_ids", Arrays.stream(studentIds).filter(Objects::nonNull).map(o -> o.toString()).collect(Collectors.joining(",")));
        params.add("course_id", courseId.toString());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<LearningResponse<Object>> response = restTemplate.exchange(builder.build().toUri(), HttpMethod.POST, requestEntity, new ParameterizedTypeReference<LearningResponse<Object>>() {
        });
//        ResponseUtils.handleDetailedResponseException(URL, response);
    }
}
