package com.alorma.github.sdk.bean.dto.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GollumPage extends ShaUrl implements Serializable {

  @SerializedName("page_name") public String name;
  public String title;
  public String summary;
  public String action;

}
