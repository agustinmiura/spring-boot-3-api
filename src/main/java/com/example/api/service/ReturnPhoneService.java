package com.example.api.service;

import com.example.api.dto.BookingDto;
import com.example.api.dto.ReturnPhoneDto;
import com.example.api.dto.ReturnPhoneResultDto;
import com.example.api.enums.CellphoneNameEnum;
import com.example.api.exception.InvalidPhoneNameException;
import com.example.api.exception.NoBookingFoundException;
import com.example.api.repository.IBookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ReturnPhoneService {

    private IBookingRepository bookingRepository;

    public ReturnPhoneService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public ReturnPhoneResultDto returnPhone(ReturnPhoneDto returnPhoneDto) {
        log.info("Return phone: {}", returnPhoneDto);

        if (!CellphoneNameEnum.validName(returnPhoneDto.getPhoneName())) {
            throw new InvalidPhoneNameException("Phone not booked");
        }

        List<BookingDto> bookings = bookingRepository.findBooking(returnPhoneDto.getPhoneName());
        var optionalBookingDto = bookings.stream().filter(bookingDto -> {
            return bookingDto.getUser().equals(returnPhoneDto.getUser()) && bookingDto.getPhone().equals(returnPhoneDto.getPhoneName());
        }).findFirst();
        if (optionalBookingDto.isEmpty()) {
            throw new NoBookingFoundException("No booking found for the user and phone");
        }
        bookingRepository.deleteById(optionalBookingDto.get().getId());
        var returnPhoneResultDto = new ReturnPhoneResultDto();
        returnPhoneResultDto.setPhoneName(returnPhoneDto.getPhoneName());
        returnPhoneResultDto.setReturnTime(LocalDateTime.now());
        returnPhoneResultDto.setUser(returnPhoneDto.getUser());
        return returnPhoneResultDto;
    }
}