package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by a557114 on 29/07/2015.
 */
public class ReleaseAsset {

  public String url;
  public String browser_download_url;
  public int id;
  public String name;
  public String labnel;
  public String state;
  public String content_type;
  public long size = 0;
  private int download_count;
  private String created_at;
  private String updated_at;
  private User uploader;

}
