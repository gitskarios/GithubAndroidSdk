package com.alorma.github.sdk.bean.dto.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bernat on 13/10/2014.
 */
public class CreateRepoRequestDTO {
	public String name;
	public String description;
	private String homepage;

	@SerializedName("private")
	public boolean isPrivate;
	public boolean has_issues;
	public boolean has_wiki;
	public boolean has_downloads;
	public boolean auto_init;
	public String gitignore_template;
	public String license_template;

}
