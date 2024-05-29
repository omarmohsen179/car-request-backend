package com.example.medical.controllers;


import com.example.medical.models.Appointment;
import com.example.medical.models.CancellationReason;
import com.example.medical.services.AppointmentService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAppointments(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
                                             @RequestParam(required = false) Long patientId) throws BadRequestException {
        if (start != null && end != null) {
            return appointmentService.getAppointmentsByDateRange(start, end);
        } else if (patientId != null) {
            return appointmentService.getAppointmentsByPatient(patientId);
        } else {
            throw new BadRequestException("Invalid request parameters");
        }
    }

    @PostMapping
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping("/{id}/cancel")
    public void cancelAppointment(@PathVariable Long id, @RequestParam CancellationReason reason, @RequestParam String reasonDetail) {
        appointmentService.cancelAppointment(id, reason, reasonDetail);
    }
}