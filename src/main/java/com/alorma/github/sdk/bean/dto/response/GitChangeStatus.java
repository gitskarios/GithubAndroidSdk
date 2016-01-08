package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

public class GitChangeStatus implements Serializable {
  public int additions;
  public int deletions;
  public int total;
  public int changes;
}