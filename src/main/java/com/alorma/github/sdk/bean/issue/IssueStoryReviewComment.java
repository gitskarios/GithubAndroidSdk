package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.ReviewComment;
import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStoryReviewComment implements IssueStoryDetail {
    public ReviewComment event;
    public long created_at;

    public IssueStoryReviewComment(ReviewComment event) {
        this.event = event;
    }

    @Override
    public boolean isList() {
        return false;
    }

    @Override
    public String getType() {
        return "review_comment";
    }

    @Override
    public long createdAt() {
        return created_at;
    }

    @Override
    public User user() {
        return event.user;
    }
}
