package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;
import java.util.List;

public class GitTree extends ShaUrl implements Serializable {

  public List<GitTreeEntry> tree;
  public boolean truncated;
}
