package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.CommitComment;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.dto.response.Organization;
import com.alorma.github.sdk.bean.dto.response.PullRequest;
import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.Team;
import com.alorma.github.sdk.bean.dto.response.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bernat on 30/09/2015.
 */
public class Payload implements Serializable {
  public String action;
  public Repo repository;
  public User sender;
  public int number;
  public PullRequest pull_request;

  @SerializedName("public") public boolean is_public;
  public Organization org;
  public String created_at;
  public Issue issue;
  public CommitComment comment;
  public Release release;
  public Team team;
  public long push_id;
  public int size;
  public int distinct_size;
  public String ref;
  public String head;
  public String before;
  public List<Commit> commits;
  public Repo forkee;
}
