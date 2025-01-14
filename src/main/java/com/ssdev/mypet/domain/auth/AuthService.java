package com.ssdev.mypet.domain.auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssdev.mypet.domain.auth.dto.AuthLoginDto;
import com.ssdev.mypet.domain.auth.dto.AuthRegisterDto;
import com.ssdev.mypet.domain.auth.exception.AuthIllegalOperationException;
import com.ssdev.mypet.domain.auth.exception.UserNotFoundException;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder encoder;

  public AuthService(UserRepository repository, PasswordEncoder encoder) {
    userRepository = repository;
    this.encoder = encoder;
  }

  public String createUser(AuthRegisterDto dto) throws Exception {
    try {
      String passkey = generateUUID();
      
      User newUser = new User();
      newUser.setUsername(dto.username());
      newUser.setPassword(encoder.encode(passkey));

      userRepository.save(newUser);
      return passkey;
    } catch (Exception e) {
      throw new AuthIllegalOperationException("FAILED WHILE CREATING USER", e);
    }
  }

  public User verifyUser(AuthLoginDto dto) throws Exception {
    Optional<User> result = userRepository
      .findByUsername(dto.username());

    if (result.isEmpty()) {
      throw new UserNotFoundException();
    }

    User user = result.get();

    if (!encoder.matches(dto.password(), user.getPassword())) {
      throw new AuthIllegalOperationException("INVALID CREDENTIALS");
    }

    return user;
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public String generateUUID() {
    return UUID.randomUUID().toString().substring(0, 20);
  }
}
