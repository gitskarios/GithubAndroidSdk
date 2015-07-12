package com.alorma.github.sdk.services.user.events;

import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.bean.dto.response.ListEvents;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 03/10/2014.
 */
public interface EventsService {

	@GET("/users/{username}/received_events")
	void events(@Path("username") String username, Callback<List<GithubEvent>> eventsCallback);

	@GET("/users/{username}/received_events")
	void events(@Path("username") String username, @Query("page") int page, Callback<List<GithubEvent>> eventsCallback);

}
