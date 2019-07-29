package com.gaodun.storm.study.common.request;

import com.gaodun.storm.study.common.EdsStatus;
import com.gaodun.storm.study.common.Exceptions;
import com.gaodun.storm.study.common.ServiceException;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class EdsRequest {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${domain.prefix}")
    private String prefix;

    public List<StudentInfo> getStudentsFromEds(Long[] studentIds) {
        String URL = String.format("http://%s%s/api/schoolClass/getNames?", prefix, Domain.EdsAPI);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);
        builder = builder.queryParam("students", Strings.join(Arrays.asList(studentIds), ','));

        String url = builder.toUriString();

//        System.out.println(url);
        ResponseEntity<EdsStatus<EdsStudentData>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<EdsStatus<EdsStudentData>>() {
        });
        if (!response.hasBody()) {
            throw new ServiceException(String.format(Exceptions.EMPTY_BODY_ERROR_FORMAT, URL));
        }
        EdsStatus<EdsStudentData> result = response.getBody();
        if (result.getStatus() != 1) {
            throw new ServiceException(String.format(Exceptions.API_ERROR_FORMAT, URL, result.getInfo()));
        }
        return result.getData().getStudents();
    }
}
