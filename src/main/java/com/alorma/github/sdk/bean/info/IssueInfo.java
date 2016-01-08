package com.alorma.github.sdk.bean.info;

import com.alorma.github.sdk.bean.dto.response.IssueState;

import java.io.Serializable;

/**
 * Created by Bernat on 06/09/2014.
 */
public class IssueInfo implements Serializable {

  public RepoInfo repoInfo;
  public int num;
  public int commentNum;
  public IssueState state = IssueState.open;

  @Override
  public String toString() {
    return repoInfo.toString() + "#" + num;
  }
}
