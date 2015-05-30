package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.PullRequest;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.dto.response.Organization;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bernat on 05/10/2014.
 */
public class PullRequestEventPayload extends ActionEventPayload {
	public int number;
	public PullRequest pull_request;

	@SerializedName("public")
	public boolean is_public;
	public Organization org;
	public String created_at;
}
