package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class GistRevision extends ShaUrl implements Parcelable {

    public static final Creator<GistRevision> CREATOR = new Creator<GistRevision>() {
        public GistRevision createFromParcel(Parcel source) {
            return new GistRevision(source);
        }

        public GistRevision[] newArray(int size) {
            return new GistRevision[size];
        }
    };
    private Date committedAt;
    private GitChangeStatus changeStatus;
    private String version;
    private User user;

    public GistRevision() {
    }

    protected GistRevision(Parcel in) {
        super(in);
        long tmpCommittedAt = in.readLong();
        this.committedAt = tmpCommittedAt == -1 ? null : new Date(tmpCommittedAt);
        this.changeStatus = in.readParcelable(GitChangeStatus.class.getClassLoader());
        this.version = in.readString();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    /**
     * @return committedAt
     */
    public Date getCommittedAt() {
        return committedAt;
    }

    /**
     * @return this gist revision
     */
    public GistRevision setCommittedAt(Date committedAt) {
        this.committedAt = committedAt;
        return this;
    }

    /**
     * @return changeStatus
     */
    public GitChangeStatus getChangeStatus() {
        return changeStatus;
    }

    /**
     * @return this gist revision
     */
    public GistRevision setChangeStatus(GitChangeStatus changeStatus) {
        this.changeStatus = changeStatus;
        return this;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return this gist revision
     */
    public GistRevision setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return this gist revision
     */
    public GistRevision setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * @return this gist revision
     */
    public GistRevision setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeLong(committedAt != null ? committedAt.getTime() : -1);
        dest.writeParcelable(this.changeStatus, 0);
        dest.writeString(this.version);
        dest.writeParcelable(this.user, 0);
    }
}