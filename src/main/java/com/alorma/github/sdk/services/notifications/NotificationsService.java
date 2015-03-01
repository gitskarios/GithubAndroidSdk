package com.alorma.github.sdk.services.notifications;

import com.alorma.github.sdk.bean.dto.request.LastDate;
import com.alorma.github.sdk.bean.dto.response.Notification;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Bernat on 18/02/2015.
 */
public interface NotificationsService {
	
	@GET("/notifications")
	void getNotifications(Callback<List<Notification>> notifications);
	
	@PUT("/repos/{owner}/{repo}/notifications")
	void markAsReadRepo(@Path("owner") String owner, @Path("repo") String repo, @Body LastDate body, Callback<Response> responseCallback);
	
	
}
