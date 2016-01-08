package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

public class GitBlob extends ShaUrl implements Serializable {

  public String content;
  public int size;
  public String encoding;
}
