package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 12/07/2014.
 */
public class Email implements Serializable {
  public String email;
  public boolean verified;
  public boolean primary;
}
