package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.ReviewComment;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStoryReviewComment extends IssueStoryDetail {
    public ReviewComment event;

    public IssueStoryReviewComment(ReviewComment event) {
        this.event = event;
    }
}
