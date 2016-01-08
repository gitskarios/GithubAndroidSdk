package com.alorma.github.sdk.bean.dto.request;

import java.io.Serializable;

/**
 * Created by Bernat on 21/06/2015.
 */
public class MergeButtonRequest implements Serializable {
  public String commit_message;
  public String sha;
}
