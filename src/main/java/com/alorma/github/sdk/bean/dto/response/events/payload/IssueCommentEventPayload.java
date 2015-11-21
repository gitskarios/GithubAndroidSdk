package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.Issue;

/**
 * Created by Bernat on 05/10/2014.
 */
public class IssueCommentEventPayload extends ActionEventPayload {
  public Issue issue;
  public GithubComment comment;
}
