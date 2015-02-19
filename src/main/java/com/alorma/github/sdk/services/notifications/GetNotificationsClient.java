package com.alorma.github.sdk.services.notifications;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.Notification;
import com.alorma.github.sdk.services.client.BaseClient;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 18/02/2015.
 */
public class GetNotificationsClient extends BaseClient<List<Notification>> {

	public GetNotificationsClient(Context context) {
		super(context);
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		restAdapter.create(NotificationsService.class).getNotifications(this);
	}
}
