package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.PullRequest;
import com.alorma.github.sdk.bean.dto.response.CommitComment;

public class PullRequestReviewCommentEventPayload extends ActionEventPayload {
  public PullRequest pull_request;
  public CommitComment comment;
}
