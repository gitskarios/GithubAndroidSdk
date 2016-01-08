package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

public class GitTreeEntry extends ShaUrl implements Serializable {

  public String path;
  public String mode;
  public int size;
  public GitTreeType type;
}
