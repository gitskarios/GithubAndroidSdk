package com.alorma.github.sdk.bean.dto.response;

/**
 * Created by Bernat on 30/05/2015.
 */
public class CommitComment extends GithubComment {
    public int id;
    public int position;
    public int line;
    public String commit_id;
    public String path;
}
