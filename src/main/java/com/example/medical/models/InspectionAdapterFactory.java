package com.example.medical.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class InspectionAdapterFactory {
    private final Map<String, InspectionAdapter> adapters;

    @Autowired
    public InspectionAdapterFactory(List<InspectionAdapter> adapterList) {
        this.adapters = adapterList.stream()
                .collect(Collectors.toMap(InspectionAdapter::getCompanyName, Function.identity()));
    }

    public InspectionAdapter getAdapter(String companyName) {
        return adapters.get(companyName);
    }
} 