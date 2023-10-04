package com.example.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PhoneDto {
    private String name;
    private boolean isAvailable = true;
    private LocalDateTime bookingTime;
    private List<String> bookingUsers = new ArrayList<>();
}
