package com.alorma.github.sdk.bean.dto.response.events.payload;

import java.io.Serializable;

/**
 * Created by Bernat on 05/10/2014.
 */
public class ActionEventPayload extends GithubEventPayload implements Serializable {
  public String action;
}
