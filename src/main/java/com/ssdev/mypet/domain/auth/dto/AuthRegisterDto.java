package com.ssdev.mypet.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthRegisterDto(
  @NotNull @NotBlank String username
) {}
