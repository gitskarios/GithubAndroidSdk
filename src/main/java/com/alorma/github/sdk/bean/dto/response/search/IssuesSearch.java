package com.alorma.github.sdk.bean.dto.response.search;

import com.alorma.github.sdk.bean.dto.response.Issue;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bernat on 08/08/2014.
 */
public class IssuesSearch extends SearchBase implements Serializable {
  public List<Issue> items;
}
