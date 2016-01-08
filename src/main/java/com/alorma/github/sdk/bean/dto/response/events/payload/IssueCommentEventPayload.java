package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.Issue;

import java.io.Serializable;

/**
 * Created by Bernat on 05/10/2014.
 */
public class IssueCommentEventPayload extends ActionEventPayload implements Serializable {
  public Issue issue;
  public GithubComment comment;
}
