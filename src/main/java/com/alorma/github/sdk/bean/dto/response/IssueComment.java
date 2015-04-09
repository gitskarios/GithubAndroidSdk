package com.alorma.github.sdk.bean.dto.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bernat on 23/08/2014.
 */
public class IssueComment extends ShaUrl{
    public String body;
    public String body_html;
    public User user;
    public String created_at;
    public String updated_at;
}
