package com.example.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TechnicalInfoDto {
    @JsonProperty("name")
    private List<String> name;

    @JsonProperty("technology")
    private List<String> technology;

    @JsonProperty("2g")
    private Map<String, List<String>> _2g;

    @JsonProperty("3g")
    private Map<String, List<String>> _3g;

    @JsonProperty("4g")
    private Map<String, List<String>> _4g;

}
