package com.alorma.github.sdk;

import com.alorma.github.sdk.bean.dto.response.Links;
import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 30/05/2015.
 */
public class PullRequest {
    public String patch_url;

    public String diff_url;

    public String body;

    public String state;

    public String commits_url;

    //public Links _links;

    public int deletions;

    public String issue_url;

    public String id;

    public Milestone milestone;

    public String mergeable_state;

    public int additions;

    public String title;

    public String comments_url;

    public String created_at;

    public String review_comment_url;

    public int commits;

    public int review_comments;

    public Head head;

    public String merged_at;

    public String closed_at;

    public Object mergeable;

    public String review_comments_url;

    public User assignee;

    public int number;

    public int changed_files;

    public String url;

    public String html_url;

    public String updated_at;

    public User merged_by;

    public Head base;

    public String statuses_url;

    public String locked;

    public User user;

    public String merge_commit_sha;

    public int comments;

    public boolean merged;
}
