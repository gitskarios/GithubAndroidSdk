package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.CommitComment;
import com.alorma.github.sdk.bean.dto.response.PullRequest;

import java.io.Serializable;

public class PullRequestReviewCommentEventPayload extends ActionEventPayload implements Serializable {
  public PullRequest pull_request;
  public CommitComment comment;
}
