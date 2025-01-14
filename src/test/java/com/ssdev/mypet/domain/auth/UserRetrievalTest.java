package com.ssdev.mypet.domain.auth;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class UserRetrievalTest {
  @MockitoBean
  UserRepository mockRepository;

  @MockitoBean
  PasswordEncoder mockPasswordEncoder;

  AuthService authService;

  @BeforeAll
  void SETUP() {
    authService = new AuthService(mockRepository, mockPasswordEncoder);
  }

  @Test
  void FIND_USER_BY_USERNAME() {
    // SETUP
    User mockUser = new User("id", "username", "password");
    when(mockRepository.findByUsername(anyString()))
      .thenReturn(Optional.of(mockUser));

    // ACT
    var result = assertDoesNotThrow(()->authService.findByUsername("username"));

    // ASSERT
    assertTrue(result.isPresent());
    assertEquals("id", result.get().getId());
  }

  @Test
  void FIND_USER_BY_USERNAME_FAILS() {
    // SETUP
    when(mockRepository.findByUsername(anyString()))
      .thenReturn(Optional.empty());

    // ACT + ASSERT
    assertTrue(authService.findByUsername("id").isEmpty());
  }
}
