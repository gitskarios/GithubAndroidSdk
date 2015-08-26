package com.alorma.github.sdk.bean.info;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Bernat on 24/05/2015.
 */
public class CommitInfo implements Parcelable {
    public RepoInfo repoInfo;
    public String sha;

    public CommitInfo() {

    }

    protected CommitInfo(Parcel in) {
        repoInfo = in.readParcelable(RepoInfo.class.getClassLoader());
        sha = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(repoInfo, flags);
        dest.writeString(sha);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CommitInfo> CREATOR = new Parcelable.Creator<CommitInfo>() {
        @Override
        public CommitInfo createFromParcel(Parcel in) {
            return new CommitInfo(in);
        }

        @Override
        public CommitInfo[] newArray(int size) {
            return new CommitInfo[size];
        }
    };
}
