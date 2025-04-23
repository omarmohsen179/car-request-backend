package com.example.medical.models;

import org.springframework.stereotype.Component;

@Component
public class AutoCheckAdapter implements InspectionAdapter {
    @Override
    public int inspectCar(String carDetails) {
        // Simulate inspection process
        return (int) (Math.random() * 100);
    }

    @Override
    public String getCompanyName() {
        return "AUTO_CHECK_CO";
    }
} 