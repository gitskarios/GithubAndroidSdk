package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;

/**
 * Created by Bernat on 23/08/2014.
 */
public class GithubComment extends ShaUrl {

  public static final Creator<GithubComment> CREATOR = new Creator<GithubComment>() {
    @Override
    public GithubComment createFromParcel(Parcel in) {
      return new GithubComment(in);
    }

    @Override
    public GithubComment[] newArray(int size) {
      return new GithubComment[size];
    }
  };
  private static final int MAX_MESSAGE_LENGHT = 146;
  public String id;
  public String body;
  public String body_html;
  public User user;
  public String created_at;
  public String updated_at;

  protected GithubComment(Parcel in) {
    super(in);
    id = in.readString();
    body = in.readString();
    body_html = in.readString();
    user = in.readParcelable(User.class.getClassLoader());
    created_at = in.readString();
    updated_at = in.readString();
  }

  public GithubComment() {

  }

  public String shortMessage() {
    if (body != null) {
      if (body.length() > MAX_MESSAGE_LENGHT) {
        return body.substring(0, MAX_MESSAGE_LENGHT).concat("...");
      } else {
        return body;
      }
    }
    return null;
  }

  @Override
  public int describeContents() {
    return super.describeContents();
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
    dest.writeString(id);
    dest.writeString(body);
    dest.writeString(body_html);
    dest.writeParcelable(user, flags);
    dest.writeString(created_at);
    dest.writeString(updated_at);
  }
}
