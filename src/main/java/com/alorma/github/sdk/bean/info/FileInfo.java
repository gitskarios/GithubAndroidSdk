package com.alorma.github.sdk.bean.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bernat on 24/05/2015.
 */
public class FileInfo implements Parcelable {
  @SuppressWarnings("unused") public static final Parcelable.Creator<FileInfo> CREATOR = new Parcelable.Creator<FileInfo>() {
    @Override
    public FileInfo createFromParcel(Parcel in) {
      return new FileInfo(in);
    }

    @Override
    public FileInfo[] newArray(int size) {
      return new FileInfo[size];
    }
  };
  public RepoInfo repoInfo;
  public String path;
  public String content;
  public String name;
  public String head;

  public FileInfo() {

  }

  protected FileInfo(Parcel in) {
    repoInfo = in.readParcelable(RepoInfo.class.getClassLoader());
    path = in.readString();
    content = in.readString();
    name = in.readString();
    head = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(repoInfo, flags);
    dest.writeString(path);
    dest.writeString(content);
    dest.writeString(name);
    dest.writeString(head);
  }
}