package com.alorma.github.sdk.bean.dto.response.events.payload;

import java.io.Serializable;

/**
 * Created by Bernat on 05/10/2014.
 */
public class CreatedEventPayload extends DeleteEventPayload implements Serializable {
  public String master_branch;
  public String description;
}
