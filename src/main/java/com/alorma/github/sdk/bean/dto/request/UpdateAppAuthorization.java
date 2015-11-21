package com.alorma.github.sdk.bean.dto.request;

import java.util.List;

/**
 * Created by Bernat on 19/02/2015.
 */
public class UpdateAppAuthorization {
  public String client_secret;
  public List<String> scopes;
  public String note;
}
