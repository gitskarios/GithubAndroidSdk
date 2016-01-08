package com.alorma.github.sdk.bean.issue;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Bernat on 18/07/2015.
 */
public class IssueStoryComparators implements Serializable {

  public static Comparator<IssueStoryDetail> ISSUE_STORY_DETAIL_COMPARATOR = new Comparator<IssueStoryDetail>() {
    @Override
    public int compare(IssueStoryDetail lhs, IssueStoryDetail rhs) {
      if (lhs.createdAt() > rhs.createdAt()) {
        return 1;
      } else if (lhs.createdAt() < rhs.createdAt()) {
        return -1;
      }
      return 0;
    }
  };
}
