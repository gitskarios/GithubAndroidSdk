package com.alorma.github.sdk.bean.issue;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStoryEvent extends IssueStoryDetail {
    public IssueEvent event;

    public IssueStoryEvent(IssueEvent event) {
        this.event = event;
    }
}
