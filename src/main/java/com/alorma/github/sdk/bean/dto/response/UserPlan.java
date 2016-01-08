package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserPlan implements Parcelable {

  @SuppressWarnings("unused") public static final Parcelable.Creator<UserPlan> CREATOR = new Parcelable.Creator<UserPlan>() {
    @Override
    public UserPlan createFromParcel(Parcel in) {
      return new UserPlan(in);
    }

    @Override
    public UserPlan[] newArray(int size) {
      return new UserPlan[size];
    }
  };
  public long collaborators;
  @SerializedName("private_repos") public long privateRepos;
  public long space;
  public String name;

  protected UserPlan(Parcel in) {
    collaborators = in.readLong();
    privateRepos = in.readLong();
    space = in.readLong();
    name = in.readString();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(collaborators);
    dest.writeLong(privateRepos);
    dest.writeLong(space);
    dest.writeString(name);
  }
}