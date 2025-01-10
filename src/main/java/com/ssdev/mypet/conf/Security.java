package com.ssdev.mypet.conf;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ssdev.mypet.domain.auth.AuthService;

@Configuration
public class Security {
  @Value("${spring.password-encoder-seed}")
  private String SEED = "";

  @Autowired
  private AuthService authService;

  String[] staticResources = {
    "/css/**",
    "/img/**",
    "/js/**"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf((csrf) -> csrf.disable())
      .authorizeHttpRequests((req) -> req
        .requestMatchers(staticResources)
        .permitAll()
        .anyRequest()
        .authenticated()
      )
      .sessionManagement((session) -> session
        .maximumSessions(1)
        .maxSessionsPreventsLogin(true)
      )
      .formLogin((form) -> form
        .loginPage("/login")
        .defaultSuccessUrl("/")
        .permitAll()
      )
      .logout((logout) -> logout
        .logoutSuccessUrl("/login?logout=true")
        .deleteCookies("JSESSIONID")
        .permitAll()
      );
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return (String username)->authService.findByUsername(username).orElseThrow();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(BCryptVersion.$2Y, 16, new SecureRandom(SEED.getBytes()));
  }
}
