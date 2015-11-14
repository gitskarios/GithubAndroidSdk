package com.alorma.github.sdk.services.notifications;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Notification;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by Bernat on 19/04/2015.
 */
public class UnsubscribeThread extends GithubClient<Response> {

    private Notification notification;

    public UnsubscribeThread(Context context, Notification notification) {
        super(context);
        this.notification = notification;
    }

    @Override
    protected Observable<Response> getApiObservable(RestAdapter restAdapter) {
        return restAdapter.create(NotificationsService.class).unsubscribeThread(String.valueOf(notification.id));
    }
}
