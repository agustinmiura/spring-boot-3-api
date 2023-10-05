package com.example.api.service;

import com.example.api.dto.BookingDto;
import com.example.api.dto.PhoneDto;
import com.example.api.enums.CellphoneNameEnum;
import com.example.api.repository.IBookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.api.enums.CellphoneNameEnum.S8;

@Slf4j
@Service
public class CellphoneService {

    private IBookingRepository bookingRepository;

    private Map<String, Integer> initialQtyMap = new HashMap<>();

    public CellphoneService(IBookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
        fillQtyMap();
    }

    public PhoneDto getPhone(String phoneName) {
        var phoneDto = new PhoneDto();
        phoneDto.setName(phoneName);
        List<BookingDto> bookings = bookingRepository.findBooking(phoneName);
        var initialQty = initialQtyMap.get(phoneName);
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

    private void fillQtyMap() {
        var list = CellphoneNameEnum.values();
        for(var item : list) {
            var qty = item == S8 ? 2 : 1;
            initialQtyMap.put(item.getFullName(),qty);
        }
    }
}
