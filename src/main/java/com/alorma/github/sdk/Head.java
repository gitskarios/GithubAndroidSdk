package com.alorma.github.sdk;

import android.os.Parcel;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.ShaUrl;
import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 30/05/2015.
 */
public class Head extends ShaUrl {

  public static final Creator<Head> CREATOR = new Creator<Head>() {
    @Override
    public Head createFromParcel(Parcel in) {
      return new Head(in);
    }

    @Override
    public Head[] newArray(int size) {
      return new Head[size];
    }
  };
  public String ref;
  public Repo repo;
  public String label;
  public User user;

  public Head(Parcel in) {
    super(in);
    ref = in.readString();
    repo = in.readParcelable(Repo.class.getClassLoader());
    label = in.readString();
    user = in.readParcelable(User.class.getClassLoader());
  }

  public Head() {
    super();
  }

  @Override
  public int describeContents() {
    return super.describeContents();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
    dest.writeString(ref);
    dest.writeParcelable(repo, flags);
    dest.writeString(label);
    dest.writeParcelable(user, flags);
  }
}
