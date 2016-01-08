package com.alorma.github.sdk.bean.info;

import java.io.Serializable;

/**
 * Created by Bernat on 24/05/2015.
 */
public class CommitInfo implements Serializable {

  public RepoInfo repoInfo;
  public String sha;

  public CommitInfo() {

  }

}
