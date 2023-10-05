package com.example.api.controller;


import com.example.api.dto.BookingDto;
import com.example.api.dto.InputBookingDto;
import com.example.api.exception.AllPhonesBookedException;
import com.example.api.exception.InvalidPhoneNameException;
import com.example.api.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        try {
            var bookingDto = bookingService.makeBooking(inputBookingDto);
            return ResponseEntity.ok(bookingDto);
        } catch(InvalidPhoneNameException invalidNameException) {
            log.error(" Error invalid name for the phone");
            var bookingDto = new BookingDto();
            bookingDto.setPhone(inputBookingDto.getPhoneName());
            return ResponseEntity.badRequest().body(bookingDto);
        } catch(AllPhonesBookedException allPhonesBookedException) {
            log.error(" All phones have been booked");
            var bookingDto = new BookingDto();
            bookingDto.setPhone(inputBookingDto.getPhoneName());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(bookingDto);
        } catch(Exception e) {
            log.error("Internal error in the backend",e);
            var bookingDto = new BookingDto();
            bookingDto.setPhone(inputBookingDto.getPhoneName());
            return ResponseEntity.internalServerError().body(bookingDto);
        }

    }

}
