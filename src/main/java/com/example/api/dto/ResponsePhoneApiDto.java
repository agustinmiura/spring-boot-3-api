package com.example.api.dto;

import lombok.Data;

@Data
public class ResponsePhoneApiDto {
    private TechnicalInfoDto technicalInfoDto;
    private int httpStatusCode;
    private String message;
}
