package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.ListCommit;

/**
 * Created by Bernat on 03/10/2014.
 */
public class PushEventPayload extends GithubEventPayload{
	public long push_id;
	public int size;
	public int distninct_size;
	public String ref;
	public String head;
	public String before;
	public ListCommit commits;
}
