package com.ssdev.mypet.domain.appointments;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class AppointmentCancelmentTest {
  @MockitoBean
  AppointmentsRepository mockRepository;

  AppointmentsService appointmentsService;

  Appointment mockAppointment = new Appointment(
    "id",
    "descriptor",
    "petTutorName",
    "petName",
    LocalDate.now().plusDays(7),
    LocalTime.now().plusHours(2),
    "contactPhone",
    "status");

  @BeforeAll
  void SETUP() {
    appointmentsService = new AppointmentsService(mockRepository);
  }

  @Test
  void CANCEL_APPOINTMENT_SUCCEEDS() {
    when(mockRepository.findById(anyString()))
      .thenReturn(Optional.of(mockAppointment));

    when(mockRepository.save(any(Appointment.class)))
      .thenReturn(mockAppointment);

    assertDoesNotThrow(() -> appointmentsService.cancelAppointment("id"));
  }

  @Test
  void CANCEL_INEXISTENT_APPOINTMENT_FAILS() {
    when(mockRepository.findById(anyString()))
      .thenReturn(Optional.empty());

    assertThrows(Exception.class, ()->appointmentsService.cancelAppointment("id"));
  }

  @Test
  void CANCEL_PAST_APPOINTMENT_FAILS() {
    Appointment mockAppointment = new Appointment(
      "id",
      "descriptor",
      "petTutorName",
      "petName",
      LocalDate.now().minusDays(1),
      LocalTime.now().minusMinutes(5),
      "contactPhone",
      "status");
    when(mockRepository.findById(anyString()))
      .thenReturn(Optional.of(mockAppointment));

    assertThrows(Exception.class, ()->appointmentsService.cancelAppointment("id"));
  }
}
