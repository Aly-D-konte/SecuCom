package com.example.SecuCom.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  @GetMapping("/all")
  public String allAccess() {
    LocalDateTime localDateTime = LocalDateTime.now();
    log.info("Welcome home Page " + localDateTime);
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    LocalDateTime localDateTime = LocalDateTime.now();
    log.info("Welcome home Page " + localDateTime);
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {

    LocalDateTime localDateTime = LocalDateTime.now();
    log.info("Welcome home Page " + localDateTime);
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    LocalDateTime localDateTime = LocalDateTime.now();
    log.info("Welcome home Page " + localDateTime);
    return "Admin Board.";
  }
}
