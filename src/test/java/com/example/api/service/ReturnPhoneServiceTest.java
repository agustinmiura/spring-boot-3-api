package com.example.api.service;

import com.example.api.client.MockPhoneClientImpl;
import com.example.api.client.PhoneApiClient;
import com.example.api.dto.InputBookingDto;
import com.example.api.dto.ReturnPhoneDto;
import com.example.api.exception.InvalidPhoneNameException;
import com.example.api.exception.NoBookingFoundException;
import com.example.api.repository.IBookingRepository;
import com.example.api.repository.InMemoryBookingRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.example.api.enums.CellphoneNameEnum.S8;
import static com.example.api.enums.CellphoneNameEnum.S9;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReturnPhoneServiceTest {

    private IBookingRepository bookingRepository = new InMemoryBookingRepositoryImpl();

    private PhoneApiClient phoneApiClient = new MockPhoneClientImpl();
    private ReturnPhoneService returnPhoneService = new ReturnPhoneService(bookingRepository);

    private CellphoneService cellPhoneService = new CellphoneService(bookingRepository, phoneApiClient);

    private BookingService bookingService = new BookingService(cellPhoneService, bookingRepository);

    @Test
    void shouldReturnAPhone() {
        var inputBookingDto = new InputBookingDto();
        inputBookingDto.setPhoneName(S9.getFullName());
        inputBookingDto.setUsername("username");
        var response = bookingService.makeBooking(inputBookingDto);
        var returnPhoneDto = new ReturnPhoneDto();
        returnPhoneDto.setPhoneName(S9.getFullName());
        returnPhoneDto.setUser("username");
        var result = returnPhoneService.returnPhone(returnPhoneDto);
        Assertions.assertEquals("username", result.getUser());
        Assertions.assertEquals(S9.getFullName(), result.getPhoneName());
    }

    @Test
    void shouldFailWithInvalidNames() {
        assertThrows(InvalidPhoneNameException.class, () -> {
            var returnPhoneDto = new ReturnPhoneDto();
            returnPhoneDto.setPhoneName("invalid");
            returnPhoneDto.setUser("username");
            returnPhoneService.returnPhone(returnPhoneDto);
        });
    }

    @Test
    void shouldFailWithNoBookingFoundException() {
        var inputBookingDto = new InputBookingDto();
        inputBookingDto.setPhoneName(S9.getFullName());
        inputBookingDto.setUsername("username");
        var response = bookingService.makeBooking(inputBookingDto);

        assertThrows(NoBookingFoundException.class, () -> {
            var returnPhoneDto = new ReturnPhoneDto();
            returnPhoneDto.setPhoneName(S9.getFullName());
            returnPhoneDto.setUser("username2");
            returnPhoneService.returnPhone(returnPhoneDto);
        });
        assertThrows(NoBookingFoundException.class, () -> {
            var returnPhoneDto = new ReturnPhoneDto();
            returnPhoneDto.setPhoneName(S8.getFullName());
            returnPhoneDto.setUser("username");
            returnPhoneService.returnPhone(returnPhoneDto);
        });
    }


}
