package com.example.api.client;

import com.example.api.dto.ResponsePhoneApiDto;
import com.example.api.dto.TechnicalInfoDto;

public interface PhoneApiClient {
    ResponsePhoneApiDto getPhoneInfo(String phoneName);
}
