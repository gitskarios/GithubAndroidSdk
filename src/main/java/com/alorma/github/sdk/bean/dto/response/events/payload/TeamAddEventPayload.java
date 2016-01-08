package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.Team;

import java.io.Serializable;

public class TeamAddEventPayload extends GithubEventPayload implements Serializable {
  public Team team;
  public Repo repository;
}
