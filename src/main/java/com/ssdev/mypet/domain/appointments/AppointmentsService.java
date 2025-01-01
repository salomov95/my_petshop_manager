package com.ssdev.mypet.domain.appointments;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AppointmentsService {
  private final AppointmentsRepository repository;

  public AppointmentsService(AppointmentsRepository repository) {
    this.repository = repository;
  }

  public void createAppointment(CreateAppointmentDto dto) {
    Appointment appointment = repository.save(new Appointment(dto));
    System.out.println("[DEBUG] " + appointment);
  }

  public List<Appointment> listAppointments(LocalDate filter) {
    return this.repository.findByDueDate(filter);
  }
}
