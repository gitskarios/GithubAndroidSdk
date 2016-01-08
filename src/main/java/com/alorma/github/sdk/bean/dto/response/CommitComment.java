package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 30/05/2015.
 */
public class CommitComment extends GithubComment implements Serializable {

  public int position;
  public int line;
  public String commit_id;
  public String path;
}
