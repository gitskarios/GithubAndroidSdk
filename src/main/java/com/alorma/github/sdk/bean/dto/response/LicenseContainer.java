package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 10/08/2015.
 */
public class LicenseContainer implements Serializable {
  public String name;
  public String path;
  public String sha;
  public int size;
  public String url;
  public String html_url;
  public String git_url;
  public String download_url;
  public String type;
  public String content;
  public String encoding;
  public License license;
}
