package com.example.api.service;

import com.example.api.dto.BookingDto;
import com.example.api.dto.PhoneDto;
import com.example.api.repository.IBookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CellphoneService {

    private IBookingRepository bookingRepository;

    public CellphoneService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public PhoneDto getPhone(String phoneName) {
        var phoneDto = new PhoneDto();
        phoneDto.setName(phoneName);
        List<BookingDto> bookings = bookingRepository.findBooking(phoneName);
        var initialQty = 10;
        var freeQty = initialQty - bookings.size();
        var available = freeQty >= 1;
        phoneDto.setAvailable(available);
        List<String> users = bookings.stream().map(BookingDto::getUser).toList();
        if (users.size()>=1) {
            phoneDto.setBookingUsers(users);
            LocalDateTime latest = bookings.stream().map(BookingDto::getBookingDate).max(LocalDateTime::compareTo).get();
            phoneDto.setBookingTime(latest);
        }
        return phoneDto;
    }
}
