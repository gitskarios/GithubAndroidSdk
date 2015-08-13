package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.alorma.github.sdk.PullRequest;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */

public class Issue extends GithubComment implements Parcelable {
    public int id;
    public int number;
    public IssueState state;
    public boolean locked;
    public String title;
    public List<Label> labels;
    public User assignee;
    public Milestone milestone;
    public int comments;
    @SerializedName("pull_request")
    public PullRequest pullRequest;
    @SerializedName("closed_at")
    public String closedAt;
    public Repo repository;

    protected Issue(Parcel in) {
        id = in.readInt();
        number = in.readInt();
        title = in.readString();
        assignee = in.readParcelable(User.class.getClassLoader());
        comments = in.readInt();
        pullRequest = in.readParcelable(PullRequest.class.getClassLoader());
        closedAt = in.readString();
        repository = in.readParcelable(Repo.class.getClassLoader());
    }

    public static final Creator<Issue> CREATOR = new Creator<Issue>() {
        @Override
        public Issue createFromParcel(Parcel in) {
            return new Issue(in);
        }

        @Override
        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(number);
        dest.writeString(title);
        dest.writeParcelable(assignee, flags);
        dest.writeInt(comments);
        dest.writeParcelable(pullRequest, flags);
        dest.writeString(closedAt);
        dest.writeParcelable(repository, flags);
    }
}
