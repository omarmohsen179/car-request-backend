package com.example.medical.repositories;

import com.example.medical.models.CustomerRequest;
import com.example.medical.models.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRequestRepository extends JpaRepository<CustomerRequest, Long> {
    Page<CustomerRequest> findByStatus(RequestStatus status, Pageable pageable);
    Page<CustomerRequest> findByCustomerId(Long customerId, Pageable pageable);
} 