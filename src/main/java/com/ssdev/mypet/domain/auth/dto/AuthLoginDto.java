package com.ssdev.mypet.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthLoginDto (
  @NotNull @NotBlank String username,
  @NotNull @NotBlank @Size(min=5) String password
) {}
