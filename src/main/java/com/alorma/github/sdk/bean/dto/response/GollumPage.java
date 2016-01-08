package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

public class GollumPage extends ShaUrl {

  public static final Creator<GollumPage> CREATOR = new Creator<GollumPage>() {
    @Override
    public GollumPage createFromParcel(Parcel in) {
      return new GollumPage(in);
    }

    @Override
    public GollumPage[] newArray(int size) {
      return new GollumPage[size];
    }
  };
  @SerializedName("page_name") public String name;
  public String title;
  public String summary;
  public String action;

  public GollumPage() {

  }

  protected GollumPage(Parcel in) {
    super(in);
    name = in.readString();
    title = in.readString();
    summary = in.readString();
    action = in.readString();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
    dest.writeString(name);
    dest.writeString(title);
    dest.writeString(summary);
    dest.writeString(action);
  }
}
