package com.alorma.github.sdk.bean.dto.request;

import java.io.Serializable;

public class LastDate implements Serializable {
  private String last_read_at;

  public LastDate(String last_read_at) {
    this.last_read_at = last_read_at;
  }
}