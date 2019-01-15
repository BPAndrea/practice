package com.home.urlshortening.repository;

import com.home.urlshortening.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  User findAllByAlias(String alias);

  List<User> findAll();

  User findByUserId(Long userId);

  void deleteByUserId(Long userId);
}
