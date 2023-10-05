package com.example.api.repository;

import com.example.api.dto.BookingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;

@Slf4j
@Service
public class InMemoryBookingRepositoryImpl implements IBookingRepository {

    private RandomGenerator randomGenerator = RandomGenerator.getDefault();

    private List<BookingDto> bookingDtoList = new ArrayList<>();

    public InMemoryBookingRepositoryImpl() {}

    @Override
    public void saveBooking(BookingDto bookingDto) {
        log.info("Saving the booking {} ", bookingDto);
        var uuid = UUID.randomUUID();
        bookingDto.setId(uuid.toString());
        bookingDtoList.add(bookingDto);
    }

    @Override
    public List<BookingDto> findBooking(String phoneName) {
        return bookingDtoList.stream().filter(dto -> dto.getPhone().equals(phoneName)).toList();
    }

    @Override
    public void deleteById(String id) {
        log.info(" Delete by id : {} ", id);
        var optionalBooking = bookingDtoList.stream().filter(dto -> dto.getId().equals(id)).findAny();
        if (optionalBooking.isPresent()) {
            bookingDtoList.remove(optionalBooking.get());
        }
    }
}
