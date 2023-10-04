package com.example.api.service;

import com.example.api.dto.BookingDto;
import com.example.api.dto.InputBookingDto;
import com.example.api.repository.IBookingRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.random.RandomGenerator;

@Slf4j
@Service
public class BookingService {

    private IBookingRepository bookingRepository;

    public BookingService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public BookingDto makeBooking(InputBookingDto inputBookingDto) {
        log.info(" I see the input dto : {}", inputBookingDto);

        var bookingDto = new BookingDto();
        bookingDto.setPhone(inputBookingDto.getPhoneName());
        bookingDto.setUser(inputBookingDto.getUsername());
        bookingDto.setBookingDate(LocalDateTime.now());

        bookingRepository.saveBooking(bookingDto);

        return bookingDto;
    }
}
