package com.ssdev.mypet.domain.auth.exception;

import com.ssdev.mypet.exception.HttpRequestException;

public class AuthIllegalOperationException extends HttpRequestException {
  public AuthIllegalOperationException(String message) {
    super(message);
  }

  public AuthIllegalOperationException(String message, Throwable cause) {
    super(message, cause);
  }
}
