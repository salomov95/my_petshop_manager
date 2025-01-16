package com.ssdev.mypet.exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpRequestExceptionHandler {
  Logger logger = LoggerFactory.getLogger(HttpRequestExceptionHandler.class);
  List<String> ERROR_REDIRECT_URLS = List.of(
    "redirect:/?error=true&internalservererror=true",
    "redirect:/?error=true&cancelmenterror=true",
    "redirect:/?error=true&creationerror=true",
    "redirect:/login?error=true",
    "redirect:/signup?error=true"
  );

  @ExceptionHandler
  public String globalExceptionHandler(Exception e) {
    logger.error(e.getLocalizedMessage());

    // Service Level Exceptions
    if (e instanceof HttpRequestException) {
      switch(e.getMessage()) {
        case "APPOINTMENT NOT FOUND OR INVALID":
        case "PAST APOINTMENT CANCELMENT NOT ALLOWED":
          return ERROR_REDIRECT_URLS.get(1);
        case "PAST APOINTMENT CREATION NOT ALLOWED":
          return ERROR_REDIRECT_URLS.get(2);
        case "FAILED WHILE CREATING USER":
          return ERROR_REDIRECT_URLS.get(4);
        default:
          return ERROR_REDIRECT_URLS.get(0);
      }
    }

    // Dto Validation Constrainy Exceptions
    if (e instanceof MethodArgumentNotValidException m) {
      if (m.getTarget().getClass().getName().equals("om.ssdev.mypet.domain.appointments.dto.CreateAppointmentDto")) {
        return ERROR_REDIRECT_URLS.get(2);
      }
      
      if (m.getTarget().getClass().getName().equals("com.ssdev.mypet.domain.auth.dto.AuthRegisterDto")) {
        return ERROR_REDIRECT_URLS.get(4);
      }
    }

    // Spring Security Exceptions
    if (e instanceof InternalAuthenticationServiceException) {
      return ERROR_REDIRECT_URLS.get(3);
    }

    // Rest Of Exceptions
    return ERROR_REDIRECT_URLS.get(0);
  }
}
