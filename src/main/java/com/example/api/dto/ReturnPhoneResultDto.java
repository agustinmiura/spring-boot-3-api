package com.example.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReturnPhoneResultDto {
    private String phoneName;
    private LocalDateTime returnTime;
    private String user;
}
