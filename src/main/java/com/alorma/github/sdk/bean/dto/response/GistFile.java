package com.alorma.github.sdk.bean.dto.response;

import com.google.gson.annotations.SerializedName;

public class GistFile {

	public int size;

	public String content;

	public String type;

	public String filename;

	@SerializedName("raw_url")
	public String rawUrl;
	
	public boolean truncated;
	
	public String language;
	
}