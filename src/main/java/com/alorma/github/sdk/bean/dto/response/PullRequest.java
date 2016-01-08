package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 30/05/2015.
 */
public class PullRequest extends Issue implements Serializable {

  public Head head;
  public Head base;
  public int additions;
  public int deletions;
  public int commits;
  public int changed_files;
  public boolean merged;
  public Boolean mergeable;
  public String patch_url;
  public String diff_url;

}
