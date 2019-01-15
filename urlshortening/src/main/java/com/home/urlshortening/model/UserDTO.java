package com.home.urlshortening.model;

import java.util.List;

public class UserDTO {
  public String status;
  public List<User> results;

  public UserDTO() {
  }

  public UserDTO(String status, List<User> results) {
    this.status = status;
    this.results = results;
  }
}
