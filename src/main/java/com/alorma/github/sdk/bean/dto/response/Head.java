package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 30/05/2015.
 */
public class Head extends ShaUrl implements Serializable {

  public String ref;
  public Repo repo;
  public String label;
  public User user;

}
