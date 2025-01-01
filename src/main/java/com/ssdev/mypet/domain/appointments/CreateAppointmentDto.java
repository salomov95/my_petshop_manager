package com.ssdev.mypet.domain.appointments;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateAppointmentDto(
  String petTutorName,
  String petName,
  String descriptor,
  LocalDate dueDate,
  LocalTime dueTime,
  String contactPhone
) {}
