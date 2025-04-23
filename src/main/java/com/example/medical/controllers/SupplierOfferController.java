package com.example.medical.controllers;

import com.example.medical.models.OfferStatus;
import com.example.medical.models.SupplierOffer;
import com.example.medical.services.SupplierOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class SupplierOfferController {
    private final SupplierOfferService supplierOfferService;

    @Autowired
    public SupplierOfferController(SupplierOfferService supplierOfferService) {
        this.supplierOfferService = supplierOfferService;
    }

    @PostMapping
    public ResponseEntity<SupplierOffer> submitOffer(
            @RequestParam Long requestId,
            @RequestParam Long supplierId,
            @RequestParam String carDetails,
            @RequestParam BigDecimal price) {
        return ResponseEntity.ok(supplierOfferService.submitOffer(requestId, supplierId, carDetails, price));
    }

    @GetMapping("/request/{requestId}")
    public ResponseEntity<List<SupplierOffer>> getOffersByRequest(
            @PathVariable Long requestId) {
        return ResponseEntity.ok(supplierOfferService.getOffersByRequestId(requestId));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<Page<SupplierOffer>> getOffersBySupplier(
            @PathVariable Long supplierId,
            Pageable pageable) {
        return ResponseEntity.ok(supplierOfferService.getOffersBySupplierId(supplierId, pageable));
    }

    @PutMapping("/{offerId}/status")
    public ResponseEntity<SupplierOffer> updateOfferStatus(
            @PathVariable Long offerId,
            @RequestParam OfferStatus newStatus) {
        return ResponseEntity.ok(supplierOfferService.updateOfferStatus(offerId, newStatus));
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<SupplierOffer> getOfferById(@PathVariable Long offerId) {
        return ResponseEntity.ok(supplierOfferService.getOfferById(offerId));
    }
} 