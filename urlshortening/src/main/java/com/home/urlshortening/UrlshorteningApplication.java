package com.home.urlshortening;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlshorteningApplication implements CommandLineRunner {


  @Value("${spring.datasource.url}")
  private String dbname;

  public static void main(String[] args) {
    SpringApplication.run(UrlshorteningApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("****************************" + dbname);
  }
}

