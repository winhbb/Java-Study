package com.gaodun.storm.study.common.request;

import com.fasterxml.jackson.databind.JsonNode;

public class KafkaMessage {
    private String key;
    private String topic;
    private JsonNode payLoad;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public JsonNode getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(JsonNode payLoad) {
        this.payLoad = payLoad;
    }
}
