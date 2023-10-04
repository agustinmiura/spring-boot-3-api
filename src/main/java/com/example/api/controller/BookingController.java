package com.example.api.controller;


import com.example.api.dto.BookingDto;
import com.example.api.dto.InputBookingDto;
import com.example.api.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("")
    public ResponseEntity<BookingDto> makeBooking(@RequestBody InputBookingDto inputBookingDto) {
        var bookingDto = bookingService.makeBooking(inputBookingDto);
        return ResponseEntity.ok(bookingDto);
    }

}
