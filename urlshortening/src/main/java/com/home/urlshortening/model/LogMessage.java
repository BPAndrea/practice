package com.home.urlshortening.model;

public class LogMessage {
  private String error;

  public LogMessage(String error) {
    this.error = error;
  }

  public LogMessage() {
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
