package com.example.medical.services;

import com.example.medical.models.CustomerRequest;
import com.example.medical.models.OfferStatus;
import com.example.medical.models.RequestStatus;
import com.example.medical.models.SupplierOffer;
import com.example.medical.repositories.CustomerRequestRepository;
import com.example.medical.repositories.SupplierOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class SupplierOfferService {
    private final SupplierOfferRepository supplierOfferRepository;
    private final CustomerRequestRepository customerRequestRepository;

    @Autowired
    public SupplierOfferService(SupplierOfferRepository supplierOfferRepository,
                              CustomerRequestRepository customerRequestRepository) {
        this.supplierOfferRepository = supplierOfferRepository;
        this.customerRequestRepository = customerRequestRepository;
    }

    @Transactional
    public SupplierOffer submitOffer(Long requestId, Long supplierId, String carDetails, BigDecimal price) {
        CustomerRequest request = customerRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        if (request.getStatus() != RequestStatus.ACTIVE) {
            throw new RuntimeException("Cannot submit offer for non-active request");
        }

        if (supplierOfferRepository.existsByRequestIdAndSupplierId(requestId, supplierId)) {
            throw new RuntimeException("Supplier has already submitted an offer for this request");
        }

        SupplierOffer offer = new SupplierOffer();
        offer.setRequest(request);
        offer.setSupplierId(supplierId);
        offer.setCarDetails(carDetails);
        offer.setPrice(price);
        offer.setStatus(OfferStatus.PENDING);

        return supplierOfferRepository.save(offer);
    }

    public Page<SupplierOffer> getOffersByRequestId(Long requestId, Pageable pageable) {
        return supplierOfferRepository.findByRequestId(requestId, pageable);
    }

    public Page<SupplierOffer> getOffersBySupplierId(Long supplierId, Pageable pageable) {
        return supplierOfferRepository.findBySupplierId(supplierId, pageable);
    }

    @Transactional
    public SupplierOffer updateOfferStatus(Long offerId, OfferStatus newStatus) {
        SupplierOffer offer = supplierOfferRepository.findById(offerId)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        offer.setStatus(newStatus);
        return supplierOfferRepository.save(offer);
    }
} 