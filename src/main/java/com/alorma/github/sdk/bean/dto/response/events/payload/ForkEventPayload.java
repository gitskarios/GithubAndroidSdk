package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.Repo;

/**
 * Created by Bernat on 05/10/2014.
 */
public class ForkEventPayload extends GithubEventPayload {
	public Repo forkee;
}
