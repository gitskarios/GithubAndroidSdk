package com.alorma.github.sdk.bean.dto.request;

import com.alorma.github.sdk.bean.dto.response.IssueState;

import java.io.Serializable;

/**
 * Created by Bernat on 23/08/2014.
 */
public class IssueRequest extends EditIssueRequestDTO implements Serializable {
  public String title;
  public String body;
  public String assignee;
  public Integer milestone;
  public String milestoneName;
  public CharSequence[] labels;
  public IssueState state;
}
