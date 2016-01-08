package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class GistFile implements Parcelable {

  public static final Creator<GistFile> CREATOR = new Creator<GistFile>() {
    @Override
    public GistFile createFromParcel(Parcel in) {
      return new GistFile(in);
    }

    @Override
    public GistFile[] newArray(int size) {
      return new GistFile[size];
    }
  };
  public int size;
  public String content;
  public String type;
  public String filename;
  @SerializedName("raw_url") public String rawUrl;
  public boolean truncated;
  public String language;

  public GistFile() {

  }

  protected GistFile(Parcel in) {
    size = in.readInt();
    content = in.readString();
    type = in.readString();
    filename = in.readString();
    rawUrl = in.readString();
    language = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(size);
    dest.writeString(content);
    dest.writeString(type);
    dest.writeString(filename);
    dest.writeString(rawUrl);
    dest.writeString(language);
  }
}