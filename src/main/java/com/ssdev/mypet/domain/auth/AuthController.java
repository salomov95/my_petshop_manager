package com.ssdev.mypet.domain.auth;

import java.security.Principal;

import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssdev.mypet.domain.auth.dto.AuthRegisterDto;
import com.ssdev.mypet.domain.auth.exception.AuthIllegalOperationException;

import jakarta.validation.Valid;

@Controller @RequestMapping
public class AuthController {
  private final AuthService service;

  public AuthController(AuthService service) {
    this.service = service;
  }

  @GetMapping("/login")
  public String login(Principal principal) {
    if (principal != null) {
      return "redirect:/";
    }

    return "login";
  }

  @GetMapping("/signup")
  public String signup(Principal principal, Model model) {
    if (principal != null) {
      String username = principal.getName();
      model.addAttribute("username", username);
      model.addAttribute("isLoggedIn", true);
   }

    AuthRegisterDto dto = new AuthRegisterDto("");
    model.addAttribute("dto", dto);
    return "signup";
  }

  @PostMapping(path={"/signup"}, consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String signUp(@Valid AuthRegisterDto dto) {
    try {
      String rawNewPasskey = service.createUser(dto);
      LoggerFactory.getLogger(AuthController.class).info("User Created With Passkey: " + rawNewPasskey);
      return "redirect:/login";
    } catch(AuthIllegalOperationException e) {
      return "redirect:/signup?error=true";
    } catch(Exception e) {
      return "redirect:/signup?error=true";
    }
  }
}
