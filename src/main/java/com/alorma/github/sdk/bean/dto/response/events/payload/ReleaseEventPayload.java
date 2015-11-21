package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 30/05/2015.
 */
public class ReleaseEventPayload extends GithubEventPayload {

  public String action;
  public Release release;
  public Repo repository;
  public User sender;
}
