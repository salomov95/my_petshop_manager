package com.ssdev.mypet.conf;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Crypto {
  @Value("${spring.password-encoder-seed}")
  private String SEED = "";

  @Bean
  public PasswordEncoder passwordEncoder() {
   return new BCryptPasswordEncoder(BCryptVersion.$2Y, 16, new SecureRandom(SEED.getBytes()));
  }
}
