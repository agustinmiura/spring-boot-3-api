package com.example.api.controller;

import com.example.api.dto.PhoneDto;
import com.example.api.service.CellphoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/cellphone")
public class CellPhoneController {

    private CellphoneService cellphoneService;

    public CellPhoneController(CellphoneService cellphoneService) {
        this.cellphoneService = cellphoneService;
    }

    @GetMapping("")
    public ResponseEntity<PhoneDto> getPhone(@RequestParam(name="name", required=true) String name) {
        log.info(" Getting the phone: {}", name);
        var phoneDto = cellphoneService.getPhone(name);
        return ResponseEntity.ok(phoneDto);
    }
}
