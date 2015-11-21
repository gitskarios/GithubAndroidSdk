package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class Gist extends ShaUrl {

  public static final Creator<Gist> CREATOR = new Creator<Gist>() {
    @Override
    public Gist createFromParcel(Parcel in) {
      return new Gist(in);
    }

    @Override
    public Gist[] newArray(int size) {
      return new Gist[size];
    }
  };
  @SerializedName("public") public boolean isPublic;
  public String created_at;
  public String updated_at;
  public int comments;
  public List<GistRevision> history;
  public Map<String, GistFile> files;
  public String description;
  @SerializedName("git_pull_url") public String gitPullUrl;
  @SerializedName("git_push_url") public String gitPushUrl;
  @SerializedName("forks_url") public String forksUrl;
  public String id;
  public User owner;
  public User user;

  public Gist() {

  }

  protected Gist(Parcel in) {
    super(in);
    created_at = in.readString();
    updated_at = in.readString();
    comments = in.readInt();
    description = in.readString();
    gitPullUrl = in.readString();
    gitPushUrl = in.readString();
    forksUrl = in.readString();
    id = in.readString();
    owner = in.readParcelable(User.class.getClassLoader());
    user = in.readParcelable(User.class.getClassLoader());
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
    dest.writeString(created_at);
    dest.writeString(updated_at);
    dest.writeInt(comments);
    dest.writeString(description);
    dest.writeString(gitPullUrl);
    dest.writeString(gitPushUrl);
    dest.writeString(forksUrl);
    dest.writeString(id);
    dest.writeParcelable(owner, flags);
    dest.writeParcelable(user, flags);
  }
}