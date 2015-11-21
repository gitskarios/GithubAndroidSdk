package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;
import android.os.Parcelable;

public class GitReference implements Parcelable {

  public static final Creator<GitReference> CREATOR = new Creator<GitReference>() {
    @Override
    public GitReference createFromParcel(Parcel in) {
      return new GitReference(in);
    }

    @Override
    public GitReference[] newArray(int size) {
      return new GitReference[size];
    }
  };
  public String ref;
  public String url;
  public GitReferenceEntry object;

  protected GitReference(Parcel in) {
    ref = in.readString();
    url = in.readString();
    object = in.readParcelable(GitReferenceEntry.class.getClassLoader());
  }

  public GitReference() {

  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(ref);
    dest.writeString(url);
    dest.writeParcelable(object, flags);
  }
}
