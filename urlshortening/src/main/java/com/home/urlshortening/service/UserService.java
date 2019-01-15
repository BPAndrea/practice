package com.home.urlshortening.service;

import com.home.urlshortening.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

  void addUser(User user);

  boolean searchForAlias(String alias);

  void increaseHitCounter(String alias);

  List<User> getAll();

  User findById(Long id);

  void deleteById(Long userId);
}
