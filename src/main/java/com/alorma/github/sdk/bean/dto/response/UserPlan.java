package com.alorma.github.sdk.bean.dto.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserPlan implements Serializable {

  public long collaborators;
  @SerializedName("private_repos") public long privateRepos;
  public long space;
  public String name;

}