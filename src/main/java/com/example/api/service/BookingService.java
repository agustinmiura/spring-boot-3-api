package com.example.api.service;

import com.example.api.dto.BookingDto;
import com.example.api.dto.InputBookingDto;
import com.example.api.enums.CellphoneNameEnum;
import com.example.api.exception.AllPhonesBookedException;
import com.example.api.exception.InvalidPhoneNameException;
import com.example.api.repository.IBookingRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.random.RandomGenerator;

@Slf4j
@Service
public class BookingService {

    private CellphoneService cellPhoneService;
    private IBookingRepository bookingRepository;

    public BookingService(
        CellphoneService cellPhoneService,
        IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        this.cellPhoneService = cellPhoneService;
    }

    public BookingDto makeBooking(InputBookingDto inputBookingDto) {
        log.info(" I see the input dto : {}", inputBookingDto);

        if (!CellphoneNameEnum.validName(inputBookingDto.getPhoneName())) {
            throw new InvalidPhoneNameException("Phone not booked");
        }

        int freeQty = cellPhoneService.getFreeQty(inputBookingDto.getPhoneName());
        if (freeQty==0) {
            throw new AllPhonesBookedException("All phones have been booked");
        }

        var bookingDto = new BookingDto();
        bookingDto.setPhone(inputBookingDto.getPhoneName());
        bookingDto.setUser(inputBookingDto.getUsername());
        bookingDto.setBookingDate(LocalDateTime.now());

        bookingRepository.saveBooking(bookingDto);

        return bookingDto;
    }
}
