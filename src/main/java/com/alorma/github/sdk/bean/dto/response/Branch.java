package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 20/07/2014.
 */
public class Branch implements Serializable {
  public String name;
  public Commit commit;
  public Links _links;

  @Override
  public String toString() {
    return name;
  }
}
