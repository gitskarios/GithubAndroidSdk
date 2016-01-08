package com.alorma.github.sdk.bean.dto.request;

import java.io.Serializable;

/**
 * Created by Bernat on 13/07/2014.
 */
public class RequestTokenDTO implements Serializable {
  public String client_id;
  public String client_secret;
  public String code;
  public String redirect_uri;
}
