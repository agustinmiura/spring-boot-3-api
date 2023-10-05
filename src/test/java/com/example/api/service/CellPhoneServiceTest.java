package com.example.api.service;

import com.example.api.client.MockPhoneClientImpl;
import com.example.api.client.PhoneApiClient;
import com.example.api.dto.InputBookingDto;
import com.example.api.dto.ReturnPhoneDto;
import com.example.api.enums.CellphoneNameEnum;
import com.example.api.exception.InvalidPhoneNameException;
import com.example.api.repository.IBookingRepository;
import com.example.api.repository.InMemoryBookingRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.example.api.enums.CellphoneNameEnum.S9;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CellPhoneServiceTest {

    private PhoneApiClient phoneApiclient = new MockPhoneClientImpl();
    private IBookingRepository bookingRepository = new InMemoryBookingRepositoryImpl();
    private CellphoneService cellPhoneService = new CellphoneService(bookingRepository, phoneApiclient);

    private BookingService bookingService = new BookingService(cellPhoneService, bookingRepository);

    @Test
    void shouldFindFreeCellPhone() {
        var info = cellPhoneService.getPhone(CellphoneNameEnum.S8.getFullName());
        Assertions.assertTrue(info.isAvailable());
        Assertions.assertEquals(CellphoneNameEnum.S8.getFullName(), info.getName());
        Assertions.assertNull(info.getBookingTime());
        Assertions.assertNotNull(info.getTechnicalInfoDto());
        Assertions.assertTrue(info.getBookingUsers().isEmpty());
    }

    @Test
    void shouldFindBookedCellPhone() {
        var inputBookingDto = new InputBookingDto();
        inputBookingDto.setPhoneName(S9.getFullName());
        inputBookingDto.setUsername("username");
        var response = bookingService.makeBooking(inputBookingDto);
        var info = cellPhoneService.getPhone(S9.getFullName());
        Assertions.assertFalse(info.isAvailable());
        Assertions.assertEquals(CellphoneNameEnum.S9.getFullName(), info.getName());
        Assertions.assertNotNull(info.getBookingTime());
        Assertions.assertNotNull(info.getTechnicalInfoDto());
        Assertions.assertTrue(info.getBookingUsers().contains("username"));
    }

    @Test
    void shouldFailWithInvalidName() {
        assertThrows(InvalidPhoneNameException.class, () -> {
            cellPhoneService.getPhone("invalidName");
        });
    }


}
