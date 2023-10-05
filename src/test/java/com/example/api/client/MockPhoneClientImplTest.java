package com.example.api.client;

import com.example.api.enums.CellphoneNameEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ResourceLoader;


public class MockPhoneClientImplTest {

    private ResourceLoader resourceLoader;

    private MockPhoneClientImpl mockPhoneClient = new MockPhoneClientImpl();

    @Test
    void shouldGetInfoForPhones() {
        var values = CellphoneNameEnum.values();
        for(CellphoneNameEnum value:values) {
            var data = mockPhoneClient.getPhoneInfo(value.getFullName());
            Assertions.assertNotNull(data.getTechnicalInfoDto());
            Assertions.assertEquals(200, data.getHttpStatusCode());
        }
    }

}
