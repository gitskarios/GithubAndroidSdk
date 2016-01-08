package com.alorma.github.sdk.bean.info;

import com.alorma.github.sdk.bean.dto.response.Permissions;

import java.io.Serializable;

/**
 * Created by Bernat on 07/09/2014.
 */
public class RepoInfo implements Serializable {

  public String owner;
  public String name;
  public String branch;
  public Permissions permissions = new Permissions();

  @Override
  public String toString() {
    return owner + "/" + name;
  }
}