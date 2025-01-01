package com.ssdev.mypet.domain.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/auth")
public class AuthController {
  private Logger logger;

  public AuthController() {
    this.logger = LoggerFactory.getLogger(AuthController.class);
  }

  @GetMapping("/login")
  public String login(Model model) {
    AuthLoginDto dto = new AuthLoginDto("");
    model.addAttribute("dto", dto);
    return "login";
  }

  @PostMapping(path={"/login"}, consumes={MediaType.APPLICATION_FORM_URLENCODED_VALUE})
  public String login(AuthLoginDto dto) {
    logger.info("[DEBUG] Passkey: {}", dto.passkey());
    return "redirect:/";
  }
}
