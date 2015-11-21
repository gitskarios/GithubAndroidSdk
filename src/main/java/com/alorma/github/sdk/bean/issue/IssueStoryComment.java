package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStoryComment implements IssueStoryDetail {

  public GithubComment comment;
  public long created_at;

  public IssueStoryComment(GithubComment comment) {

    this.comment = comment;
  }

  @Override
  public boolean isList() {
    return false;
  }

  @Override
  public String getType() {
    return "commented";
  }

  @Override
  public long createdAt() {
    return created_at;
  }

  @Override
  public User user() {
    return comment.user;
  }
}
