package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.User;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Bernat on 07/04/2015.
 */
public class PullRequestStoryCommitsList extends ArrayList<Commit> implements IssueStoryDetail, Serializable{

  public long created_at;
  public User user;

  @Override
  public boolean isList() {
    return true;
  }

  @Override
  public String getType() {
    return "pushed";
  }

  @Override
  public long createdAt() {
    return created_at;
  }

  @Override
  public User user() {
    return user;
  }
}
