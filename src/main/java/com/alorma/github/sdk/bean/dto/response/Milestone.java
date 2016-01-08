package com.alorma.github.sdk.bean.dto.response;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bernat on 22/08/2014.
 */
public class Milestone extends ShaUrl implements Comparable<Milestone>{

  public String title;
  public int number;
  public MilestoneState state;
  public String description;
  public User creator;
  @SerializedName("open_issues") public int openIssues;
  @SerializedName("closes_issues") public int closedIssues;
  @SerializedName("created_at") public String createdAt;
  @SerializedName("updated_at") public String updatedAt;
  @SerializedName("due_on") public String dueOn;

  @Override
  public int compareTo(Milestone another) {
    return title.toLowerCase().compareTo(another.title.toLowerCase());
  }
}
