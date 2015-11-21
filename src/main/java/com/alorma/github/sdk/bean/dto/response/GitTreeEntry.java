package com.alorma.github.sdk.bean.dto.response;

public class GitTreeEntry extends ShaUrl {

  public String path;
  public String mode;
  public int size;
  public GitTreeType type;
}
