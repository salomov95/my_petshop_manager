package com.ssdev.mypet.domain.auth;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.ssdev.mypet.domain.auth.dto.AuthRegisterDto;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class UserCreationTest {
  @MockitoBean
  PasswordEncoder mockEncoder;

  @MockitoBean
  UserRepository mockRepository;

  AuthService authService;

  @BeforeAll
  void SETUP() {
    authService = new AuthService(mockRepository, mockEncoder);
  }

  @Test
  void CREATE_USER() {
    // SETUP
    User mockUser = new User("id", "username", "password");
    AuthRegisterDto mockDto = new AuthRegisterDto("username");
    when(mockEncoder.encode(anyString())).thenReturn("af38ae01fcb91a9b2ba947baefd");
    when(mockRepository.save(any(User.class))).thenReturn(mockUser);

    // ACT
    var result = assertDoesNotThrow(()->authService.createUser(mockDto));

    // ASSERT
    assertNotEquals(null, result);
  }
}
