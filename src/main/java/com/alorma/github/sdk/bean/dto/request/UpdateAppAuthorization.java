package com.alorma.github.sdk.bean.dto.request;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bernat on 19/02/2015.
 */
public class UpdateAppAuthorization implements Serializable {
  public String client_secret;
  public List<String> scopes;
  public String note;
}
