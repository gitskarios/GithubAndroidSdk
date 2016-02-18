package com.alorma.github.sdk.bean.issue;

import android.os.Parcel;
import android.os.Parcelable;
import com.alorma.github.sdk.bean.dto.response.GithubStatusResponse;
import com.alorma.github.sdk.bean.dto.response.PullRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernat on 07/04/2015.
 */
public class PullRequestStory implements Parcelable {
  public static final Creator<PullRequestStory> CREATOR = new Creator<PullRequestStory>() {
    public PullRequestStory createFromParcel(Parcel source) {
      return new PullRequestStory(source);
    }

    public PullRequestStory[] newArray(int size) {
      return new PullRequestStory[size];
    }
  };
  public PullRequest pullRequest;
  public List<IssueStoryDetail> details;
  public GithubStatusResponse statusResponse;

  public PullRequestStory() {
  }

  protected PullRequestStory(Parcel in) {
    this.pullRequest = in.readParcelable(PullRequest.class.getClassLoader());
    this.details = new ArrayList<IssueStoryDetail>();
    in.readList(this.details, List.class.getClassLoader());
    this.statusResponse = in.readParcelable(GithubStatusResponse.class.getClassLoader());
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(this.pullRequest, 0);
    dest.writeList(this.details);
    dest.writeParcelable(this.statusResponse, 0);
  }
}
