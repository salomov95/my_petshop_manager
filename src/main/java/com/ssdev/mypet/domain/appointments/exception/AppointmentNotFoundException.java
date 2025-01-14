package com.ssdev.mypet.domain.appointments.exception;

import com.ssdev.mypet.exception.HttpRequestException;

public class AppointmentNotFoundException extends HttpRequestException {
  private final static String MESSAGE = "APPOINTMENT NOT FOUND OR INVALID";

  public AppointmentNotFoundException() {
    super(MESSAGE);
  }

  public AppointmentNotFoundException(Throwable cause) {
    super(MESSAGE, cause);
  }
}
