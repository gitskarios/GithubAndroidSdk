package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.Issue;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStory implements Serializable {
  public Issue issue;
  public List<IssueStoryDetail> details;
}
