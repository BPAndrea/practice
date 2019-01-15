package com.home.urlshortening.service;

import com.home.urlshortening.model.User;
import com.home.urlshortening.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void addUser(User user) {
    if (user != null) {
      userRepository.save(user);
    }
  }

  @Override
  public boolean searchForAlias(String alias) {
    User toReturn = userRepository.findAllByAlias(alias);
    if (toReturn == null) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void increaseHitCounter(String alias) {
    User toReturn = userRepository.findAllByAlias(alias);
    toReturn.setHitCount(toReturn.getHitCount() + 1);
    userRepository.save(toReturn);
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public User findById(Long id) {
    return userRepository.findByUserId(id);
  }

  @Override
  public void deleteById(Long userId) {
    userRepository.deleteById(userId);
  }


}
