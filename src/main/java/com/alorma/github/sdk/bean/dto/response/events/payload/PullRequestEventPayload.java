package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Organization;
import com.alorma.github.sdk.bean.dto.response.PullRequest;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Bernat on 05/10/2014.
 */
public class PullRequestEventPayload extends ActionEventPayload implements Serializable {
  public int number;
  public PullRequest pull_request;

  @SerializedName("public") public boolean is_public;
  public Organization org;
  public String created_at;
}
