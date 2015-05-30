package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.GithubComment;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStoryComment extends IssueStoryDetail{

    public GithubComment comment;

    public IssueStoryComment(GithubComment comment) {

        this.comment = comment;
    }
}
