package com.alorma.github.sdk.bean.dto.request;

import java.io.Serializable;

public class CommentRequest implements Serializable {

  public String body;

  public CommentRequest(String body) {
    this.body = body;
  }
}
