package com.alorma.github.sdk.services.user.events;

import com.alorma.github.sdk.bean.dto.response.ListEvents;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Bernat on 03/10/2014.
 */
public interface EventsService {

	@GET("/users/{username}/received_events")
	void events(@Path("username") String username, Callback<ListEvents> eventsCallback);

}
