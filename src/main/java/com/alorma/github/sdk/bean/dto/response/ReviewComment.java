package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 17/07/2015.
 */
public class ReviewComment implements Serializable {
  public String url;
  public int id;
  public String diff_hunk;
  public String path;
  public int position;
  public int original_position;
  public String commit_id;
  public String original_commit_id;
  public User user;
  public String body;
  public String created_at;
  public String updated_at;
  public String html_url;
  public String pull_request_url;
}
