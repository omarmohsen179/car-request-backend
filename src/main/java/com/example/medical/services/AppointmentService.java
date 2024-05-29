package com.example.medical.services;


import com.example.medical.exceptions.ResourceNotFoundException;
import com.example.medical.models.Appointment;
import com.example.medical.models.CancellationReason;
import com.example.medical.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAppointmentsByDateRange(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByAppointmentDateBetween(start, end);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long id, CancellationReason reason, String reasonDetail) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        appointment.setCancellationReason(reason);
        appointment.setReasonForCancellation(reasonDetail);
        appointmentRepository.save(appointment);
    }
}