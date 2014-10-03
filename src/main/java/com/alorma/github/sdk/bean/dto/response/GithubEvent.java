package com.alorma.github.sdk.bean.dto.response;

import com.alorma.github.sdk.bean.dto.response.events.EventType;
import com.alorma.github.sdk.bean.dto.response.events.payload.EventPayload;

/**
 * Created by Bernat on 31/08/2014.
 */
public class GithubEvent {
	public int id;
	public EventType type = EventType.Unhandled;
	public String name;
	public User actor;
	public Repo repo;
	public EventPayload payload;
}
