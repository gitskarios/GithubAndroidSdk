package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Repo;

import java.io.Serializable;

/**
 * Created by Bernat on 05/10/2014.
 */
public class ForkEventPayload extends GithubEventPayload implements Serializable {
  public Repo forkee;
  public Repo repository;
}
