package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.CommitComment;
import com.alorma.github.sdk.bean.dto.response.PullRequest;

public class PullRequestReviewCommentEventPayload extends ActionEventPayload {
  public PullRequest pull_request;
  public CommitComment comment;
}
