package com.gaodun.storm.study.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Getter
@Setter
public class DeployStatus<T> {
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public DeployStatus() {
        this.status = "1";
    }

    public static DeployStatus status() {
        Path filePath = Paths.get("./DEPLOY");
        DeployStatus status = new DeployStatus();
        try (Stream<String> stream = Files.lines(filePath, StandardCharsets.UTF_8)) {
            Map valueMap = new HashMap();
            stream.forEach(line -> {
                String[] values = line.split("\\|");
                if (values.length == 2) {
                    valueMap.put(values[0], values[1]);
                } else if (values.length == 1) {
                    valueMap.put(values[0], "");
                }
            });
            status.setData(valueMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }
}
