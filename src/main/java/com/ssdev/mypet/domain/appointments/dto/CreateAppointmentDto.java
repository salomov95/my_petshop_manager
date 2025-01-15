package com.ssdev.mypet.domain.appointments.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateAppointmentDto(
  @NotNull @NotBlank @NotEmpty String petTutorName,
  @NotNull @NotBlank @NotEmpty String petName,
  @NotNull @NotBlank @NotEmpty String descriptor,
  @NotNull @FutureOrPresent LocalDate dueDate,
  @NotNull @FutureOrPresent LocalTime dueTime,
  @NotNull @NotBlank @NotEmpty String contactPhone
) {}
