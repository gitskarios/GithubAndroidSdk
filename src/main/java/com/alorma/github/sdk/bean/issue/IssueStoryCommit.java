package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStoryCommit implements IssueStoryDetail {

    public Commit commit;
    public long created_at;

    public IssueStoryCommit(Commit commit) {
        this.commit = commit;
    }

    @Override
    public boolean isList() {
        return false;
    }

    @Override
    public String getType() {
        return "committed";
    }

    @Override
    public long createdAt() {
        return created_at;
    }

    @Override
    public User user() {
        return commit.committer;
    }
}
