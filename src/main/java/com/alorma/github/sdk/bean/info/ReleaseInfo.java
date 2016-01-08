package com.alorma.github.sdk.bean.info;

import java.io.Serializable;

/**
 * Created by Bernat on 06/09/2014.
 */
public class ReleaseInfo implements Serializable {

  public RepoInfo repoInfo;
  public int num;

  public ReleaseInfo(RepoInfo repoInfo) {
    this.repoInfo = repoInfo;
  }

  @Override
  public String toString() {
    return repoInfo.toString() + " #" + num;
  }
}
