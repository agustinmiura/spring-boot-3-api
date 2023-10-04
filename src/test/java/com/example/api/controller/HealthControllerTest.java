package com.example.api.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HealthControllerTest {

    private HealthController healthController = new HealthController();

    @Test
    public void shouldGetStatus() {
        String status = healthController.getStatus();
        Assertions.assertEquals("success", status);

    }
}
