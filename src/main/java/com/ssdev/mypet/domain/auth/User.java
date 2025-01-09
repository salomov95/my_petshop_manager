package com.ssdev.mypet.domain.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(name="tb_users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(exclude={"password"})
public class User implements UserDetails {
  @Id @GeneratedValue(strategy=GenerationType.UUID)
  private String id;

  @Column(nullable=false)
  private String username;

  @Column(nullable=false)
  private String password;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLES.USER"));
  }
}
