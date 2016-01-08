package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by a557114 on 06/09/2015.
 */
public class GithubStatus implements Serializable {
  public int id;
  public String created_at;
  public String updated_at;
  public String state;
  public String target_url;
  public String description;
  public String url;
  public String context;
}
