package com.example.api.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
public class BookingDto {

    private String id;

    private String phone;

    private LocalDateTime bookingDate;

    private String user;

}
