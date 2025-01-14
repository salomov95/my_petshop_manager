package com.ssdev.mypet.domain.auth.exception;

import com.ssdev.mypet.exception.HttpRequestException;

public class UserNotFoundException extends HttpRequestException {
  public UserNotFoundException() {
    super("USER NOT FOUND");
  }

  public UserNotFoundException(Throwable cause) {
    super("USER NOT FOUND", cause);
  }
}
