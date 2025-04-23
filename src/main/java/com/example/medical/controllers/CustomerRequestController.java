package com.example.medical.controllers;

import com.example.medical.models.CustomerRequest;
import com.example.medical.models.InspectionCompany;
import com.example.medical.models.RequestStatus;
import com.example.medical.services.CustomerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class CustomerRequestController {
    private final CustomerRequestService customerRequestService;

    @Autowired
    public CustomerRequestController(CustomerRequestService customerRequestService) {
        this.customerRequestService = customerRequestService;
    }

    @PostMapping
    public ResponseEntity<CustomerRequest> createRequest(
            @RequestParam Long customerId,
            @RequestParam String description,
            @RequestParam InspectionCompany inspectionCompany) {
        return ResponseEntity.ok(customerRequestService.createRequest(customerId, description, inspectionCompany));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerRequest>> getRequests(
            @RequestParam(required = false) RequestStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        if (status != null) {
            return ResponseEntity.ok(customerRequestService.getRequestsByStatus(status, pageRequest));
        }
        return ResponseEntity.ok(customerRequestService.getRequestsByStatus(RequestStatus.ACTIVE, pageRequest));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Page<CustomerRequest>> getCustomerRequests(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        return ResponseEntity.ok(customerRequestService.getRequestsByCustomerId(customerId, pageRequest));
    }

    @PutMapping("/{requestId}/status")
    public ResponseEntity<CustomerRequest> updateRequestStatus(
            @PathVariable Long requestId,
            @RequestParam RequestStatus newStatus) {
        return ResponseEntity.ok(customerRequestService.updateRequestStatus(requestId, newStatus));
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<CustomerRequest> getRequestById(@PathVariable Long requestId) {
        return ResponseEntity.ok(customerRequestService.getRequestById(requestId));
    }
} 