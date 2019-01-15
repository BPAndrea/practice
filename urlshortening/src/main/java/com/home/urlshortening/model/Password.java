package com.home.urlshortening.model;

import javax.persistence.*;

@Entity
public class Password {
  @Id
  @GeneratedValue
  private Long id;
  private int password;
  //private Long user_id= Long.valueOf(0);

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;


  public Password() {

  }

  public Password(Long id, int password, User user) {
    this.id = id;
    this.password = password;
    this.user = user;
  }

  public Password(int password, User user) {
    this.password = password;
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

 /* public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }*/

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getPassword() {
    return password;
  }

  public void setPassword(int password) {
    this.password = password;
  }
}
