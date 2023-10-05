package com.example.api.client;

import com.example.api.dto.ResponsePhoneApiDto;
import com.example.api.dto.TechnicalInfoDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MockPhoneClientImpl implements PhoneApiClient {

    private ResourceLoader resourceLoader;

    private Map<String, TechnicalInfoDto> infoMap = new HashMap<>();

    public MockPhoneClientImpl() {
        initResources();
    }

    public ResponsePhoneApiDto getPhoneInfo(String phoneName) {
        var responseDto = new ResponsePhoneApiDto();
        if (infoMap.containsKey(phoneName)) {
            responseDto.setTechnicalInfoDto(infoMap.get(phoneName));
            responseDto.setHttpStatusCode(HttpStatus.OK.value());
            responseDto.setMessage("OK");
        } else {
            responseDto.setTechnicalInfoDto(null);
            responseDto.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
            responseDto.setMessage("");
        }
        return responseDto;
    }

    private void initResources() {
        log.info("Initializing resources");
        try {
            ClassPathResource resource = new ClassPathResource("phones.json");
            String json = Files.lines(Path.of(resource.getURI()), StandardCharsets.UTF_8)
                    .collect(Collectors.joining("\n"));
            ObjectMapper objectMapper = new ObjectMapper();
            List<TechnicalInfoDto> phones = objectMapper.readValue(json, new TypeReference<List<TechnicalInfoDto>>() {
            });
            for (TechnicalInfoDto phone : phones) {
               infoMap.put(phone.getName().get(0), phone);
            }
        } catch (Exception e) {
            log.error("Error initializing resources", e);
        }
    }

}
