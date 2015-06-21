package com.alorma.github.sdk.bean.dto.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */

public class Issue extends GithubComment {
    public int number;
    public IssueState state;
    public boolean locked;
    public String title;
    public List<Label> labels;
    public User assignee;
    public Milestone milestone;
    public int comments;
    @SerializedName("pull_request")
    public Issue pullRequest;
    @SerializedName("closed_at")
    public String closedAt;
    public Repo repository;
}
