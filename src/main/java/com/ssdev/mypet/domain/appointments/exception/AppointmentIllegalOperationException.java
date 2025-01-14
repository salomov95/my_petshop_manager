package com.ssdev.mypet.domain.appointments.exception;

import com.ssdev.mypet.exception.HttpRequestException;

public class AppointmentIllegalOperationException extends HttpRequestException {
  public AppointmentIllegalOperationException() {
    super("ILLEGAL APPOINTMENT OPERATION");
  }

  public AppointmentIllegalOperationException(String message) {
    super(message);
  }

  public AppointmentIllegalOperationException(String message, Throwable cause) {
    super(message, cause);
  }
}
