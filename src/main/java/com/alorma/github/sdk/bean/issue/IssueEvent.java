package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.Label;
import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.dto.response.User;

import java.io.Serializable;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueEvent implements Serializable {
  public int id;
  public String url;
  public User actor;
  public String event;
  public String commit_id;
  public String created_at;
  public Label label;
  public Milestone milestone;
  public User assignee;
  public Rename rename;
}
