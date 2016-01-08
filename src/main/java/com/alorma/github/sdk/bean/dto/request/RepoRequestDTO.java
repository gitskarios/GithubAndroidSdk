package com.alorma.github.sdk.bean.dto.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Bernat on 13/10/2014.
 */
public class RepoRequestDTO implements Serializable {

  public String name;
  public String description;
  public String homepage;
  @SerializedName("private") public boolean isPrivate;
  public boolean has_issues;
  public boolean has_wiki;
  public boolean has_downloads;
  public String default_branch;
  public boolean auto_init;
  public String gitignore_template;
  public String license_template;
  public int team_id;

  public boolean isValid() {
    return !isEmpty(name);
  }

  private boolean isEmpty(String s) {
    return s == null || s.isEmpty();
  }
}
