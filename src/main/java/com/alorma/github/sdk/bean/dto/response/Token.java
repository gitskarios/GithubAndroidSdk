package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 13/07/2014.
 */
public class Token implements Serializable {
  public String access_token;
  public String token_type;
  public String scope;

  public String error;
  public String error_description;
  public String error_uri;
}
