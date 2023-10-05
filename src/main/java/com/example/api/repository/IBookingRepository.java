package com.example.api.repository;

import com.example.api.dto.BookingDto;

import java.util.List;

public interface IBookingRepository {
    public void saveBooking(BookingDto bookingDto);
    public List<BookingDto> findBooking(String phoneName);

    public void deleteById(String id);
}
