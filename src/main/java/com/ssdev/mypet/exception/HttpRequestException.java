package com.ssdev.mypet.exception;

public class HttpRequestException extends RuntimeException {
  public HttpRequestException(String message) {
    super(message);
  }

  public HttpRequestException(Throwable cause) {
    super(cause);
  }

  public HttpRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}
