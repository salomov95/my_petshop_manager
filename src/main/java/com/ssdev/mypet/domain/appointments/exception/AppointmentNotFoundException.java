package com.ssdev.mypet.domain.appointments.exception;

import com.ssdev.mypet.exception.HttpRequestException;
import com.ssdev.mypet.exception.HttpRequestExceptionMessages;

public class AppointmentNotFoundException extends HttpRequestException {
  public AppointmentNotFoundException() {
    super(HttpRequestExceptionMessages.APPOINTMENT_NOT_FOUND_OR_INVALID);
  }

  public AppointmentNotFoundException(Throwable cause) {
    super(HttpRequestExceptionMessages.APPOINTMENT_NOT_FOUND_OR_INVALID, cause);
  }
}
