package com.alorma.github.sdk.bean.dto.response;

import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */
public class Contributor {
  public User author;
  public int id;
  public int total;
  public List<Week> weeks;
}
