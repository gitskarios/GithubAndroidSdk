package com.alorma.github.sdk.bean.dto.response.events.payload;

import com.alorma.github.sdk.bean.dto.response.UserType;

/**
 * Created by Bernat on 05/10/2014.
 */
public class DeleteEventPayload extends GithubEventPayload {
	public String ref;
	public String ref_type;
	public UserType pusher_type;
}
