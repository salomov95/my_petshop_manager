package com.ssdev.mypet.domain.appointments;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class AppointmentsService {
  private final AppointmentsRepository repository;

  public AppointmentsService(AppointmentsRepository repository) {
    this.repository = repository;
  }

  public String createAppointment(CreateAppointmentDto dto) {
    Appointment appointment = this.repository.save(new Appointment(dto));
    return appointment.getId();
  }

  public void cancelAppointment(String appointmentId) throws Exception {
    Optional<Appointment> ap = this.repository.findById(appointmentId);

    if (ap.isEmpty()) {
      throw new Exception("Appointment not found");
    }

    Appointment appointment = ap.get();
    appointment.setStatus("APPOINTMENTS.CANCELLED");
    repository.save(appointment);
  }

  public List<Appointment> listAppointments(LocalDate filter) {
    return this.repository.findByDueDate(filter);
  }
}
