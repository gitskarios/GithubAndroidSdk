package com.alorma.github.sdk.bean.dto.request;

import com.alorma.github.sdk.bean.dto.response.IssueState;

/**
 * Created by Bernat on 15/04/2015.
 */
public class CreateMilestoneRequestDTO {

  public String title;
  public String description;
  public String due_on;
  public IssueState state;

  public CreateMilestoneRequestDTO(String title) {
    this.title = title;
  }
}
