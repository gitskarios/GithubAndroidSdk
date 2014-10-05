package com.alorma.github.sdk.bean.dto.response;

import com.alorma.github.sdk.bean.dto.response.events.EventType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bernat on 31/08/2014.
 */
public class GithubEvent {
	public long id;
	public EventType type = EventType.Unhandled;
	public String name;
	public User actor;
	public Repo repo;
	public Object payload;

	@SerializedName("public")
	public boolean public_event;

	public String created_at;

	public EventType getType() {
		return type != null ? type : EventType.Unhandled;
	}
}
