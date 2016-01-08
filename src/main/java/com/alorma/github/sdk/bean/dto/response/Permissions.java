package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 13/07/2014.
 */
public class Permissions implements Serializable {

  public boolean admin;
  public boolean push;
  public boolean pull;

  public Permissions() {
    this.admin = false;
    this.push = false;
    this.pull = false;
  }
}
