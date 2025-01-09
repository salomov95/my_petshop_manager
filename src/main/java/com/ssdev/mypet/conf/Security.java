package com.ssdev.mypet.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import com.ssdev.mypet.domain.auth.AuthService;

@Configuration
public class Security {
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
      .csrf((customCsrf) -> customCsrf.disable())
      .formLogin((customForm) -> customForm
        .loginPage("/auth/login")
        .defaultSuccessUrl("/")
        .permitAll()
      )
      .logout((customLogout) -> customLogout
        .logoutSuccessUrl("/auth/login?logout=true")
        .deleteCookies("JSESSIONID")
      )
      .authorizeHttpRequests((req) -> req
        .requestMatchers(staticResources)
        .permitAll()
        .anyRequest()
        .authenticated()
      );
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return (String username)->authService.findByUsername(username).orElseThrow();
  }
}
