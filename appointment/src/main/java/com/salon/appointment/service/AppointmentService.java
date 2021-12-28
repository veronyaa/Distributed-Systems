package com.salon.appointment.service;

import com.salon.appointment.exception.AppointmentNotFoundException;
import com.salon.appointment.repo.AppointmentRepository;
import com.salon.appointment.repo.model.Appointment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository)
    {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAppointments()
    {
        return appointmentRepository.findAll();
    }

    public Appointment saveAppointments(Appointment newAppointment) {
        return appointmentRepository.save(newAppointment);
    }

    public Appointment getAppointmentById(Long id_appointment) {
        Optional<Appointment> appointment = appointmentRepository.findById(id_appointment);
        if (appointment.isPresent()) {
            log.info("appointment: {}", appointment.get());
            return appointment.get();
        }
        throw new AppointmentNotFoundException();
    }
    public Appointment updateAppointmentById(Long id_appointment, Appointment updatedAppointment) {
        Optional<Appointment> appointment = appointmentRepository.findById(id_appointment);
        if (appointment.isPresent()) {
            Appointment oldAppointment = appointment.get();
            log.info("appointment: {}", oldAppointment);
            updateAppointment(oldAppointment, updatedAppointment);
            return appointmentRepository.save(oldAppointment);
        }
        throw new AppointmentNotFoundException();
    }

    private void updateAppointment(Appointment oldAppointment, Appointment updatedAppointment) {
        oldAppointment.setIdUser(updatedAppointment.getIdUser());
        oldAppointment.setIdService(updatedAppointment.getIdService());
        oldAppointment.setStatus(updatedAppointment.getStatus());


    }

    public String deleteAppointmentById(Long id_appointment) {
        appointmentRepository.deleteById(id_appointment);
        return "Appointment was successfully deleted!";
    }
}


