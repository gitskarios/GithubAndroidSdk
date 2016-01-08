package com.alorma.github.sdk.bean.dto.request;

import java.io.Serializable;

public class CommitCommentRequest implements Serializable {

  public String body;
  public String path;
  public int position;
}
