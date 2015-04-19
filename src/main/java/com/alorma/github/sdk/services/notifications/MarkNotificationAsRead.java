package com.alorma.github.sdk.services.notifications;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Notification;
import com.alorma.github.sdk.services.client.BaseClient;

import retrofit.RestAdapter;
import retrofit.client.Response;

/**
 * Created by Bernat on 19/04/2015.
 */
public class MarkNotificationAsRead extends BaseClient<Response> {

    private Notification notification;

    public MarkNotificationAsRead(Context context, Notification notification) {
        super(context);
        this.notification = notification;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(NotificationsService.class).markThreadAsRead(String.valueOf(notification.id), this);
    }
}
