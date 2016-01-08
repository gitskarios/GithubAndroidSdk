package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;

/**
 * Created by Bernat on 20/07/2014.
 */
public class ShaUrl implements Serializable{

  private static final int MAX_SHA_LENGHT = 8;
  public String sha;
  public String url;
  public String html_url;

  public ShaUrl() {

  }

  public static String shortShaStatic(String sha) {
    int start = 0;
    int end = Math.min(MAX_SHA_LENGHT, sha.length());

    return sha.substring(start, end);
  }

  public String shortSha() {
    int start = 0;
    int end = Math.min(MAX_SHA_LENGHT, sha.length());

    return sha.substring(start, end);
  }
}
