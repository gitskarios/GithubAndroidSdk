package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.Commit;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStoryCommit extends IssueStoryDetail {

    public Commit commit;

    public IssueStoryCommit(Commit commit) {
        this.commit = commit;
    }
}
