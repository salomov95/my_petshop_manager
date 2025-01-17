package com.ssdev.mypet.domain.appointments;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ssdev.mypet.domain.appointments.dto.CreateAppointmentDto;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
class AppointmentCreationTest {
  @MockitoBean
  AppointmentsRepository mockRepository;

  AppointmentsService appointmentsService;

  @BeforeAll
  void SETUP() {
    appointmentsService = new AppointmentsService(mockRepository);
  }

  @Test
  void CREATE_APPOINTMENT_SUCCEEDS() {
    // SETUP
    CreateAppointmentDto mockDto = new CreateAppointmentDto(
      "petTutorName",
      "petName",
      "descriptor",
      LocalDate.now().plusDays(7),
      LocalTime.now().plusHours(3),
      "contactPhone");
    
    Appointment mockAppointment = new Appointment(mockDto);
    mockAppointment.setId("id");
    
    when(mockRepository.save(any(Appointment.class)))
      .thenReturn(mockAppointment);

    // ACT
    var result = assertDoesNotThrow(
      ()->appointmentsService.createAppointment(mockDto));

    // ASSERT
    assertNotEquals(null, result);
  }

  @Test
  void CREATE_APPOINTMENT_FAILS() {
    // AFT + ASSERT
    assertThrows(NullPointerException.class, ()->appointmentsService.createAppointment(null));
  }

  @Test
  void CREATE_PAST_APPOINTMENT_FAILS() {
    // SETUP
    CreateAppointmentDto mockDto = new CreateAppointmentDto(
      "petTutorName",
      "petName",
      "descriptor",
      LocalDate.now(),
      LocalTime.now().minusHours(5),
      "contactPhone"
    );

    // ACT + ASSERT
    assertThrows(Exception.class, ()->appointmentsService.createAppointment(mockDto));
  }
}
