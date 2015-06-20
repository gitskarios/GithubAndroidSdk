package com.alorma.github.sdk;

import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.dto.response.IssueState;
import com.alorma.github.sdk.bean.dto.response.Links;
import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 30/05/2015.
 */
public class PullRequest extends Issue{
    /*public String patch_url;

    public String diff_url;

    public String body;

    public IssueState state;

    public String commits_url;

    //public Links _links;

    public String issue_url;

    public String id;

    public Milestone milestone;

    public String mergeable_state;


    public String title;

    public String comments_url;

    public String created_at;

    public String review_comment_url;

    public int commits;

    public int review_comments;


    public String merged_at;

    public String closed_at;

    public String review_comments_url;

    public User assignee;

    public int number;

    public String url;

    public String html_url;

    public String updated_at;

    public User merged_by;

    public String statuses_url;

    public User user;

    public String merge_commit_sha;

    public int comments;*/

    public Head head;

    public Head base;

    public int additions;

    public int deletions;

    public int commits;

    public int changed_files;

    public boolean merged;

    public Boolean mergeable;
}
