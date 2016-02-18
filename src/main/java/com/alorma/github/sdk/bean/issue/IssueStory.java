package com.alorma.github.sdk.bean.issue;

import android.os.Parcel;
import android.os.Parcelable;
import com.alorma.github.sdk.bean.dto.response.Issue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStory implements Parcelable {
  public static final Creator<IssueStory> CREATOR = new Creator<IssueStory>() {
    public IssueStory createFromParcel(Parcel source) {
      return new IssueStory(source);
    }

    public IssueStory[] newArray(int size) {
      return new IssueStory[size];
    }
  };
  public Issue issue;
  public List<IssueStoryDetail> details;

  public IssueStory() {
  }

  protected IssueStory(Parcel in) {
    this.issue = in.readParcelable(Issue.class.getClassLoader());
    this.details = new ArrayList<IssueStoryDetail>();
    in.readList(this.details, List.class.getClassLoader());
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(this.issue, 0);
    dest.writeList(this.details);
  }
}
