package com.alorma.github.sdk.services.notifications;

import com.alorma.github.sdk.bean.dto.Notification;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Bernat on 18/02/2015.
 */
public interface NotificationsService {
	
	@GET("/notifications")
	void getNotifications(Callback<List<Notification>> notifications);
	
}
