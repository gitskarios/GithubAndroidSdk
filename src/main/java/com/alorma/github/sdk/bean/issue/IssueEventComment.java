package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.issue.IssueEvent;
import com.alorma.github.sdk.bean.issue.IssueStoryDetail;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueEventComment extends IssueStoryDetail {
    private IssueEvent event;

    public IssueEventComment(IssueEvent event) {

        this.event = event;
    }
}
