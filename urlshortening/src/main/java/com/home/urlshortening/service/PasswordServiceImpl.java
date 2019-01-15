package com.home.urlshortening.service;

import com.home.urlshortening.model.Password;
import com.home.urlshortening.model.User;
import com.home.urlshortening.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {
  private PasswordRepository passwordRepository;

  @Autowired
  public PasswordServiceImpl(PasswordRepository passwordRepository) {
    this.passwordRepository = passwordRepository;
  }


  @Override
  public int getRandom(User user) {
    int password = (int) (Math.random() * 8880) + 1000;
    //passwordRepository.save(new Password(password, id));
    passwordRepository.save(new Password(password, user));

    return password;
  }

  @Override
  public Password passwordExits(int password) {
    return passwordRepository.findByPassword(password);
  }

  @Override
  public Password findPasswordByUser(User user) {
    return passwordRepository.findByUser(user);
  }

  @Override
  public void delete(Password password) {
    passwordRepository.delete(password);
  }
}
