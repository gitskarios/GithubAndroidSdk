package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.User;

import java.io.Serializable;

public class PublicEventPayload extends GithubEventPayload implements Serializable {

  public Repo repository;
  public User sender;
}
