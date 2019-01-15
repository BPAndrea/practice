package com.home.urlshortening.controller;

import com.home.urlshortening.model.LogMessage;
import com.home.urlshortening.model.Password;
import com.home.urlshortening.model.User;
import com.home.urlshortening.model.UserDTO;
import com.home.urlshortening.service.PasswordService;
import com.home.urlshortening.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
  private UserService userService;
  private PasswordService passwordService;

  @Autowired
  public RestController(UserService userService, PasswordService passwordService) {
    this.userService = userService;
    this.passwordService = passwordService;
  }

  @GetMapping("/api/links")
 /* 1. verzió: csak listát ad vissza:
  public List<User> getAll() {
     return userService.getAll();*/
  public UserDTO getApiResponse() {
    UserDTO result = new UserDTO("OK", userService.getAll());
    return result;
  }

  @DeleteMapping("/api/links/{id}")
  public Object deleteAlias(@PathVariable(value = "id") Long userId, @RequestBody Password password) {
    User toDelete = userService.findById(userId);
    if (toDelete == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      // LogMessage("Status code: 404 - it doesn't exist");
    } else {
      Password toReturn = passwordService.findPasswordByUser(toDelete);
      if (toReturn.getPassword() == password.getPassword()) {
        passwordService.delete(toReturn);
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        //return new LogMessage("204 status code - Account was deleted");
      } else {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
      }
    }
  }

    @PostMapping("/create")
    public ResponseEntity<User> createLogMessage (@RequestBody User user){
      if (userService.searchForAlias(user.getAlias())) {
        userService.addUser(user);
        int password = passwordService.getRandom(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(user, HttpStatus.CONFLICT);
      }

    }

    @GetMapping("/user")
    public User getUser () {
      return userService.findById((long) 11);
    }
  }
