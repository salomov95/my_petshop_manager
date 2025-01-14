package com.ssdev.mypet.domain.appointments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class AppointmentRetrievalTest {
  @MockitoBean
  AppointmentsRepository mockRepository;

  AppointmentsService appointmentsService;

  @BeforeAll
  void SETUP() {
    appointmentsService = new AppointmentsService(mockRepository);
  }

  @Test
  void LIST_APPOINTMENTS_SUCCEEDS() {
    // SETUP
    Appointment appointment = new Appointment(
      "id",
      "descriptor",
      "petTutorName",
      "petName",
      LocalDate.now(),
      LocalTime.now(),
      "contactPhone",
      "status"
    );

    when(mockRepository.findByDueDate(any(LocalDate.class)))  
      .thenReturn(List.of(appointment));

    // ACT
    List<Appointment> result = appointmentsService.listAppointments(LocalDate.now());

    // ASSERT
    assertNotEquals(null, result);
    assertNotEquals(0, result.size());
  }

  @Test
  void LIST_NO_APPOINTMENTS() {
    // SETUP
    LocalDate filter = LocalDate.now();

    // AFT
    var result = appointmentsService.listAppointments(filter);

    // ASSERT
    assertNotEquals(null, result);
    assertEquals(0, result.size());
  }
}
