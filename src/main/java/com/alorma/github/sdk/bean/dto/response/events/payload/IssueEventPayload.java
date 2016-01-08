package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Issue;

import java.io.Serializable;

/**
 * Created by Bernat on 28/05/2015.
 */
public class IssueEventPayload extends ActionEventPayload implements Serializable {
  public Issue issue;
}
