package com.example.api.service;

import com.example.api.dto.ReturnPhoneDto;
import com.example.api.dto.ReturnPhoneResultDto;
import com.example.api.repository.IBookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ReturnPhoneService {

    private IBookingRepository bookingRepository;

    public ReturnPhoneService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public ReturnPhoneResultDto returnPhone(ReturnPhoneDto returnPhoneDto) {
        log.info("Return phone: {}", returnPhoneDto);
        var returnPhoneResultDto = new ReturnPhoneResultDto();
        returnPhoneResultDto.setPhoneName(returnPhoneDto.getPhoneName());
        returnPhoneResultDto.setReturnTime(LocalDateTime.now());
        returnPhoneResultDto.setUser(returnPhoneDto.getUser());
        return returnPhoneResultDto;
    }
}