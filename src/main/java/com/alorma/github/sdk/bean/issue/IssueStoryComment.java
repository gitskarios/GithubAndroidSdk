package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.IssueComment;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStoryComment extends IssueStoryDetail{

    public IssueComment comment;

    public IssueStoryComment(IssueComment comment) {

        this.comment = comment;
    }
}
