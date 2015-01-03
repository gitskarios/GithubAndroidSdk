package com.alorma.github.sdk.bean.dto.response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Gist {

    @SerializedName("public")
	public boolean isPublic;

	public Date createdAt;

	public Date updatedAt;

	public int comments;

	public List<GistRevision> history;

	public Map<String, GistFile> files;

	public String description;

	@SerializedName("git_pull_url")
	public String gitPullUrl;

	@SerializedName("git_push_url")
	public String gitPushUrl;

    @SerializedName("forks_url")
	public String forksUrl;

	public String htmlUrl;

	public String id;

	public String url;

	public User owner;
	
	public User user;

}