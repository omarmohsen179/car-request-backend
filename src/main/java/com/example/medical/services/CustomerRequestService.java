package com.example.medical.services;

import com.example.medical.models.CustomerRequest;
import com.example.medical.models.InspectionCompany;
import com.example.medical.models.RequestStatus;
import com.example.medical.repositories.CustomerRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerRequestService {
    private final CustomerRequestRepository customerRequestRepository;

    @Autowired
    public CustomerRequestService(CustomerRequestRepository customerRequestRepository) {
        this.customerRequestRepository = customerRequestRepository;
    }

    @Transactional
    public CustomerRequest createRequest(Long customerId, String description, InspectionCompany inspectionCompany) {
        CustomerRequest request = new CustomerRequest();
        request.setCustomerId(customerId);
        request.setDescription(description);
        request.setCheckedByCompany(inspectionCompany);
        request.setStatus(RequestStatus.ACTIVE);
        return customerRequestRepository.save(request);
    }

    public Page<CustomerRequest> getRequestsByStatus(RequestStatus status, Pageable pageable) {
        return customerRequestRepository.findByStatus(status, pageable);
    }

    public Page<CustomerRequest> getRequestsByCustomerId(Long customerId, Pageable pageable) {
        return customerRequestRepository.findByCustomerId(customerId, pageable);
    }

    @Transactional
    public CustomerRequest updateRequestStatus(Long requestId, RequestStatus newStatus) {
        CustomerRequest request = customerRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(newStatus);
        return customerRequestRepository.save(request);
    }
} 