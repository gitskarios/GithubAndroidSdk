package com.alorma.github.sdk.bean.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bernat on 06/09/2014.
 */
public class ReleaseInfo implements Parcelable {

  @SuppressWarnings("unused") public static final Creator<ReleaseInfo> CREATOR = new Creator<ReleaseInfo>() {
    @Override
    public ReleaseInfo createFromParcel(Parcel in) {
      return new ReleaseInfo(in);
    }

    @Override
    public ReleaseInfo[] newArray(int size) {
      return new ReleaseInfo[size];
    }
  };
  public RepoInfo repoInfo;
  public int num;

  public ReleaseInfo() {

  }

  public ReleaseInfo(RepoInfo repoInfo) {
    this.repoInfo = repoInfo;
  }

  protected ReleaseInfo(Parcel in) {
    repoInfo = in.readParcelable(RepoInfo.class.getClassLoader());
    num = in.readInt();
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(repoInfo, flags);
    dest.writeInt(num);
  }

  @Override
  public String toString() {
    return repoInfo.toString() + " #" + num;
  }
}
