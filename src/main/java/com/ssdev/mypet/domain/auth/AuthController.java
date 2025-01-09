package com.ssdev.mypet.domain.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller @RequestMapping("/auth")
public class AuthController {
  private Logger logger;

  private final AuthService service;

  public AuthController(AuthService service) {
    this.logger = LoggerFactory.getLogger(AuthController.class);
    this.service = service;
  }

  @GetMapping("/login")
  public String login(Model model) {
    AuthLoginDto dto = new AuthLoginDto("", "");
    model.addAttribute("dto", dto);
    return "login";
  }

  @GetMapping("/signup")
  public String signup(Model model, HttpSession session) {
    logger.info("[DEBUG] Session", session.getId());
    AuthRegisterDto dto = new AuthRegisterDto("");
    model.addAttribute("dto", dto);
    return "signup";
  }

  @PostMapping(path={"/signup"}, consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String signUp(AuthRegisterDto dto) {
    logger.info(String.format("[DEBUG] Username provided: %s",dto.username()));
    try {
      String rawNewPasskey = service.createUser(dto.username());
      logger.info("[INFO] User Created With Passkey: " + rawNewPasskey);
    } catch(Exception e) {
      logger.error("[ERROR] Attempt To Register Failed", e);
      return "redirect:/auth/signup?error=true";
    }
    return "redirect:/auth/login";
  }
}
