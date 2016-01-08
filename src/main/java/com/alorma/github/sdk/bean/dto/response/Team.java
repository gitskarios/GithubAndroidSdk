package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 17/05/2015.
 */
public class Team implements Serializable {
  public int id;
  public String url;
  public String name;
  public String slug;
  public String description;
  public String permission;
  public String members_url;
  public String repositories_url;
  public int members_count;
  public int repos_count;
  public Organization organization;
}
