package com.alorma.github.sdk.bean.dto.request;

import com.alorma.github.sdk.bean.dto.response.IssueState;

/**
 * Created by Bernat on 15/04/2015.
 */
public class EditIssueRequestDTO {
    public String title;
    public String body;
    public String assignee;
    public IssueState state;
    public int milestone;
    public String[] labels;
}
