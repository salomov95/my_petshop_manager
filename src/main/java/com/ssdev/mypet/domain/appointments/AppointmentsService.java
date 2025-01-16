package com.ssdev.mypet.domain.appointments;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssdev.mypet.domain.appointments.dto.CreateAppointmentDto;
import com.ssdev.mypet.domain.appointments.exception.AppointmentIllegalOperationException;
import com.ssdev.mypet.domain.appointments.exception.AppointmentNotFoundException;

@Service
public class AppointmentsService {
  private final AppointmentsRepository repository;

  public AppointmentsService(AppointmentsRepository repository) {
    this.repository = repository;
  }

  public String createAppointment(CreateAppointmentDto dto) throws Exception {
    final String EXCEPTION_MESSAGE = "CREATE PAST APPOINTMENTS IS NOT ALLOWED";
    if (dto.dueDate().isBefore(LocalDate.now())) {
      throw new AppointmentIllegalOperationException(EXCEPTION_MESSAGE);
    }

    if (dto.dueTime().isBefore(LocalTime.now())) {
      throw new AppointmentIllegalOperationException(EXCEPTION_MESSAGE);
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

    final String EXCEPTION_MESSAGE = "CANCELL PAST APPOINTMENTS IS NOT ALLOWED";
    if (appointment.getDueDate().isBefore(LocalDate.now())) {
      throw new AppointmentIllegalOperationException(EXCEPTION_MESSAGE);
    }

    if (appointment.getDueTime().isBefore(LocalTime.now())) {
      throw new AppointmentIllegalOperationException(EXCEPTION_MESSAGE);
    }

    appointment.setStatus("APPOINTMENTS.CANCELLED");
    repository.save(appointment);
  }

  public List<Appointment> listAppointments(LocalDate filter) {
    return this.repository.findByDueDate(filter);
  }
}
