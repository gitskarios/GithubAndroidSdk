package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.User;

public class PublicEventPayload extends GithubEventPayload {

  public Repo repository;
  public User sender;
}
