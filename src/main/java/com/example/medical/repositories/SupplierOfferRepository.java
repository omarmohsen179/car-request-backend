package com.example.medical.repositories;

import com.example.medical.models.SupplierOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierOfferRepository extends JpaRepository<SupplierOffer, Long> {
    Page<SupplierOffer> findByRequestId(Long requestId, Pageable pageable);
    List<SupplierOffer> findByRequestId(Long requestId);
    Page<SupplierOffer> findBySupplierId(Long supplierId, Pageable pageable);
    boolean existsByRequestIdAndSupplierId(Long requestId, Long supplierId);
} 