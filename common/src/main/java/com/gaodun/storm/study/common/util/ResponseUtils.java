package com.gaodun.storm.study.common.util;

import com.gaodun.storm.study.common.ServiceException;
import com.gaodun.storm.study.common.StatusCode;
import com.gaodun.storm.study.common.request.response.LearningResponse;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static <T> void handleDetailedResponseException(String URL, ResponseEntity<LearningResponse<T>> response) {
        if (!response.hasBody()) {
            throw new ServiceException(StatusCode.REQUEST_FAILED, String.format("接口无响应，URL:%s", URL));
        } else {
            LearningResponse result = response.getBody();
            if (result.getStatus() != 0) {
                throw new ServiceException(StatusCode.REQUEST_FAILED, String.format("URL:%s,Message:%s", URL, result.getMessage()));
            }
        }
    }
}
