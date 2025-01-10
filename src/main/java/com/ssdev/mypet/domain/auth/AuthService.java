package com.ssdev.mypet.domain.auth;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  @Value("${spring.password-encoder-seed}")
  private String SEED = "";

  private Logger logger = LoggerFactory.getLogger(AuthService.class);
  private final UserRepository userRepository;
  private final PasswordEncoder encoder;

  public AuthService(UserRepository repository) {
    userRepository = repository;
    encoder = new BCryptPasswordEncoder(BCryptVersion.$2Y, 16, new SecureRandom(SEED.getBytes()));
  }

  public String createUser(String username) throws Exception {
    try {
      String passkey = UUID.randomUUID().toString().substring(0, 20);
      
      User newUser = new User();
      newUser.setUsername(username);
      newUser.setPassword(encoder.encode(passkey));

      userRepository.save(newUser);
      return passkey;
    } catch (Exception e) {
      logger.info("[ERROR] Error creating user", e);
      throw new Exception("FAILED WHILE CREATING USER");
    }
  }

  public User verifyUser(AuthLoginDto dto) throws Exception {
    User user = userRepository
      .findByUsername(dto.username())
      .orElseThrow(()->new Exception("USER NOT FOUND"));

    if (!encoder.matches(dto.password(), user.getPassword())) {
      throw new Exception("INVALID CREDENTIALS");
    }

    return user;
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
