package com.alorma.github.sdk.services.user.events;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.ListEvents;
import com.alorma.github.sdk.services.client.BaseClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 03/10/2014.
 */
public class GetUserEventsClient extends BaseClient<ListEvents> {
	private String username;
	private int page = 0;

	public GetUserEventsClient(Context context, String username) {
		super(context);
		this.username = username;
	}
	public GetUserEventsClient(Context context, String username, int page) {
		super(context);
		this.username = username;
		this.page = page;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		if (page == 0) {
			restAdapter.create(EventsService.class).events(username, this);
		} else {
			restAdapter.create(EventsService.class).events(username, page, this);
		}
	}
}
