package com.example.api.controller;

import com.example.api.dto.ReturnPhoneDto;
import com.example.api.dto.ReturnPhoneResultDto;
import com.example.api.service.ReturnPhoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/cellphonereturn")
public class ReturnPhoneController {

    private ReturnPhoneService returnPhoneService;

    public ReturnPhoneController(ReturnPhoneService returnPhoneService) {
        this.returnPhoneService = returnPhoneService;
    }

    @PostMapping("")
    public ResponseEntity<ReturnPhoneResultDto> returnPhone(@RequestBody ReturnPhoneDto returnPhoneDto) {
        log.info(" Return the phone : {} ", returnPhoneDto);
        var returnPhoneResultDto = returnPhoneService.returnPhone(returnPhoneDto);
        return ResponseEntity.ok(returnPhoneResultDto);
    }


}
