package com.ssdev.mypet.domain.auth;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class AuthenticationServiceUnitTests {
  @MockitoBean
  UserRepository mockUserRepository;

  @MockitoBean
  PasswordEncoder mockPasswordEncoder;

  AuthService authService;

  String mockUserId = "jadu-jwudh-ia4r-kjajBwJJwi02J";
  User mockUser = new User();

  @BeforeAll
  void setupOnce() {
    authService = new AuthService(mockUserRepository, mockPasswordEncoder);

    mockUser.setId(mockUserId);
    mockUser.setUsername("username");
    mockUser.setPassword("af38ae01fcb91a9b2ba947baefd");
  }

  @BeforeEach
  void setupEach() {
    when(mockUserRepository.findByUsername(anyString()))
      .thenReturn(Optional.of(mockUser));
  }

  @Test
  void CREATE_USER() {
    when(mockPasswordEncoder.encode(anyString()))
      .thenReturn("af38ae01fcb91a9b2ba947baefd");

    when(mockUserRepository.save(any(User.class)))
      .thenReturn(mockUser);

    var result = assertDoesNotThrow(()->authService.createUser("username"));
    assertNotEquals(null, result);
  }

  @Test
  void VERIFY_USER() {
    when(mockPasswordEncoder.matches(anyString(), anyString()))
      .thenReturn(true);

    AuthLoginDto mockLoginDto = new AuthLoginDto("username","password");
    assertDoesNotThrow(()->authService.verifyUser(mockLoginDto));
  }

  @Test
  void FIND_USER_BY_USERNAME() {
    var result = assertDoesNotThrow(()->authService.findByUsername("username"));
    assertTrue(result.isPresent());
    assertEquals(mockUserId, result.get().getId());
  }
}
