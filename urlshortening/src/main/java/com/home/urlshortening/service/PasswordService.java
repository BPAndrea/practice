package com.home.urlshortening.service;

import com.home.urlshortening.model.Password;
import com.home.urlshortening.model.User;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
  int getRandom(User user);

  Password passwordExits(int password);

  Password findPasswordByUser(User user);

  void delete(Password password);
}
