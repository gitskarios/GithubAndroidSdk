package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.Team;

public class TeamAddEventPayload extends GithubEventPayload {
  public Team team;
  public Repo repository;
}
