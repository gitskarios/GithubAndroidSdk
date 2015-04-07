package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueEvent {
    public int id;
    public String url;
    public User actor;
    public String event;
    public String commit_id;
    public String created_at;
}
