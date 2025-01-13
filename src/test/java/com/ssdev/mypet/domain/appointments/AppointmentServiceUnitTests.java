package com.ssdev.mypet.domain.appointments;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class AppointmentServiceUnitTests {
  @MockitoBean
  AppointmentsRepository appointmentsRepository;

  AppointmentsService appointmentsService;

  LocalDate TODAY = LocalDate.now();
  String mockAppointmentId = "qjdu3h-bsuB-uawhwu-jaushwiwrb-uwi3";
  CreateAppointmentDto mockDto = new CreateAppointmentDto(
    "petTutorName",
    "petName",
    "descriptor",
    TODAY,
    LocalTime.now(),
    "contactPhone");
  
  Appointment mockAppointment = new Appointment(mockDto);

  @BeforeAll
  void setupOnce() {
    appointmentsService = new AppointmentsService(appointmentsRepository);
    mockAppointment.setId(mockAppointmentId);
  }

  @BeforeEach
  void setupEach() {
    when(appointmentsRepository.save(any(Appointment.class)))
      .thenReturn(mockAppointment);
  }

  @Test
  void CREATE_APPOINTMENT() {
    var result = assertDoesNotThrow(()->appointmentsService.createAppointment(mockDto));
    assertNotEquals(null, result);
  }

  @Test
  void LIST_APPOINTMENTS() {
    when(appointmentsRepository.findByDueDate(any(LocalDate.class)))
      .thenReturn(List.of(mockAppointment));

    List<Appointment> result = appointmentsService
      .listAppointments(TODAY);
    
    assertNotEquals(null, result);
    assertNotEquals(0, result.size());
  }

  @Test
  void CANCEL_APPOINTMENT() {
    when(appointmentsRepository.findById(mockAppointmentId))
      .thenReturn(Optional.of(mockAppointment));

    assertDoesNotThrow(() -> appointmentsService
      .cancelAppointment(mockAppointmentId));
  }
}
