package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 18/02/2015.
 */
public class NotificationSubject extends ShaUrl implements Serializable {
  public String title;
  public String latest_comment_url;
  public String type;
}
