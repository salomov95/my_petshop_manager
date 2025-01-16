package com.ssdev.mypet.domain.appointments;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssdev.mypet.domain.appointments.dto.CreateAppointmentDto;
import com.ssdev.mypet.domain.appointments.exception.AppointmentIllegalOperationException;
import com.ssdev.mypet.domain.appointments.exception.AppointmentNotFoundException;
import com.ssdev.mypet.exception.HttpRequestExceptionMessages;

@Service
public class AppointmentsService {
  private final AppointmentsRepository repository;

  public AppointmentsService(AppointmentsRepository repository) {
    this.repository = repository;
  }

  public String createAppointment(CreateAppointmentDto dto) throws Exception {
    if (dto.dueDate().isBefore(LocalDate.now())) {
      throw new AppointmentIllegalOperationException(HttpRequestExceptionMessages.PAST_APOINTMENT_CREATION_NOT_ALLOWED.toString());
    }

    if (dto.dueDate().equals(LocalDate.now()) && dto.dueTime().isBefore(LocalTime.now())) {
      throw new AppointmentIllegalOperationException(HttpRequestExceptionMessages.PAST_APOINTMENT_CREATION_NOT_ALLOWED.toString());
    }

    Appointment appointment = this.repository.save(new Appointment(dto));
    return appointment.getId();
  }

  public void cancelAppointment(String appointmentId) throws Exception {
    Optional<Appointment> ap = this.repository.findById(appointmentId);

    if (ap.isEmpty()) {
      throw new AppointmentNotFoundException();
    }

    Appointment appointment = ap.get();

    if (appointment.getDueDate().isBefore(LocalDate.now())) {
      throw new AppointmentIllegalOperationException(HttpRequestExceptionMessages.PAST_APOINTMENT_CANCELMENT_NOT_ALLOWED.toString());
    }

    if (appointment.getDueDate().equals(LocalDate.now()) && appointment.getDueTime().isBefore(LocalTime.now())) {
      throw new AppointmentIllegalOperationException(HttpRequestExceptionMessages.PAST_APOINTMENT_CANCELMENT_NOT_ALLOWED.toString());
    }

    appointment.setStatus("APPOINTMENTS.CANCELLED");
    repository.save(appointment);
  }

  public List<Appointment> listAppointments(LocalDate filter) {
    return this.repository.findByDueDate(filter);
  }
}
