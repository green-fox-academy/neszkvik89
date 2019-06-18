package com.example.movie.controller;

import com.example.movie.JWTDemo;
import com.example.movie.config.JWTCsrfTokenRepository;
import com.example.movie.model.UserProfile;
import com.example.movie.repository.IUserRepository;
import com.example.movie.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegController {

  private SecretService secretService;
  private IUserRepository iUserRepository;
  private JWTCsrfTokenRepository jwtCsrfTokenRepository;

  @Autowired
  public RegController(SecretService secretService,
      IUserRepository iUserRepository) {
    this.secretService = secretService;
    this.iUserRepository = iUserRepository;
  }

  @GetMapping("/register")
  public String loadRegPage() {
    return "register";
  }

  @PostMapping("/register")
  public String doRegistration (String userName, String password) {
    if (!iUserRepository.existsByUserName(userName)) {
      iUserRepository.save(new UserProfile(userName, password));
    }
    return "login";
  }

  @PostMapping("login")
  public String doLogin (String userName, String password) {
    if (iUserRepository.findByUserName(userName).getPassword().equals(password)) {
      JWTDemo.createJWT(String.valueOf(iUserRepository.findByUserName(userName).getId()), "FBI", userName, 1560868417046L);
    }
    return "index";
  }

}