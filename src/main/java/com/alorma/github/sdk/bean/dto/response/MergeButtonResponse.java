package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 21/06/2015.
 */
public class MergeButtonResponse extends ShaUrl implements Serializable {

  public Boolean merged;
  public String message;
}
