package com.home.urlshortening.repository;

import com.home.urlshortening.model.Password;
import com.home.urlshortening.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepository extends CrudRepository<Password, Long> {
  Password findByPassword(int password);

  Password findByUser(User user);
}
