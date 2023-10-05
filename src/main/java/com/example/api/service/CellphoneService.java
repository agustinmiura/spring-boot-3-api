package com.example.api.service;

import com.example.api.client.PhoneApiClient;
import com.example.api.dto.BookingDto;
import com.example.api.dto.PhoneDto;
import com.example.api.enums.CellphoneNameEnum;
import com.example.api.repository.IBookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    private PhoneApiClient phoneApiClient;

    private Map<String, Integer> initialQtyMap = new HashMap<>();

    public CellphoneService(IBookingRepository bookingRepository, PhoneApiClient phoneApiClient) {
        this.bookingRepository = bookingRepository;
        this.phoneApiClient = phoneApiClient;
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
        fillTechnicalInfo(phoneDto);

        return phoneDto;
    }

    private void fillTechnicalInfo(PhoneDto phoneDto) {
        log.info("Filling tecnical info for phone: {}", phoneDto.getName());
        var responseDto = phoneApiClient.getPhoneInfo(phoneDto.getName());
        if (responseDto.getHttpStatusCode() == HttpStatus.OK.value()) {
            phoneDto.setTechnicalInfoDto(responseDto.getTechnicalInfoDto());
        } else {
            log.error("Error getting technical info for phone: {}", phoneDto.getName());
        }

        phoneDto.setTechnicalInfoDto(responseDto.getTechnicalInfoDto());
    }

    private void fillQtyMap() {
        var list = CellphoneNameEnum.values();
        for(var item : list) {
            var qty = item == S8 ? 2 : 1;
            initialQtyMap.put(item.getFullName(),qty);
        }
    }
}
