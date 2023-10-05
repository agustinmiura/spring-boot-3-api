package com.example.api.service;

import com.example.api.client.PhoneApiClient;
import com.example.api.dto.InputBookingDto;
import com.example.api.exception.AllPhonesBookedException;
import com.example.api.exception.InvalidPhoneNameException;
import com.example.api.repository.IBookingRepository;
import com.example.api.repository.InMemoryBookingRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.example.api.enums.CellphoneNameEnum.S8;
import static com.example.api.enums.CellphoneNameEnum.S9;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BookingServiceTest {

    private PhoneApiClient phoneApiclient;
    private IBookingRepository bookingRepository = new InMemoryBookingRepositoryImpl();
    private CellphoneService cellPhoneService = new CellphoneService(bookingRepository, phoneApiclient);

    private BookingService bookingService = new BookingService(cellPhoneService, bookingRepository);

    @Test
    void shouldBookACellPhone() {
        var inputBookingDto = new InputBookingDto();
        inputBookingDto.setPhoneName(S9.getFullName());
        inputBookingDto.setUsername("username");
        var response = bookingService.makeBooking(inputBookingDto);
        Assertions.assertEquals("username", response.getUser());
        Assertions.assertEquals(S9.getFullName(), response.getPhone());
        Assertions.assertNotNull(response.getBookingDate());
        Assertions.assertNotNull(response.getId());
    }

    @Test
    void shouldBookTwiceS8() {

        var inputBookingDto = new InputBookingDto();
        inputBookingDto.setPhoneName(S8.getFullName());
        inputBookingDto.setUsername("username");

        var response = bookingService.makeBooking(inputBookingDto);

        Assertions.assertEquals("username", response.getUser());
        Assertions.assertEquals(S8.getFullName(), response.getPhone());
        Assertions.assertNotNull(response.getBookingDate());
        Assertions.assertNotNull(response.getId());

        response = bookingService.makeBooking(inputBookingDto);

        Assertions.assertEquals("username", response.getUser());
        Assertions.assertEquals(S8.getFullName(), response.getPhone());
        Assertions.assertNotNull(response.getBookingDate());
        Assertions.assertNotNull(response.getId());

    }

    @Test
    void shouldFailWithInvalidNames() {
        assertThrows(InvalidPhoneNameException.class, () -> {
            var inputBookingDto = new InputBookingDto();
            inputBookingDto.setPhoneName("invalid");
            inputBookingDto.setUsername("username");
            bookingService.makeBooking(inputBookingDto);
        });
    }

    @Test
    void shouldFailBookingAPhoneBeingUsed() {
        assertThrows(AllPhonesBookedException.class, () -> {
            var inputBookingDto = new InputBookingDto();
            inputBookingDto.setPhoneName(S9.getFullName());
            inputBookingDto.setUsername("username");
            var response = bookingService.makeBooking(inputBookingDto);
            bookingService.makeBooking(inputBookingDto);
        });
    }

}
