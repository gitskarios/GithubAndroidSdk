package com.alorma.github.sdk.services.notifications;

import com.alorma.github.sdk.bean.dto.response.Notification;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import retrofit.client.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Bernat on 19/04/2015.
 */
public class UnsubscribeThread extends GithubClient<Boolean> {

  private Notification notification;

  public UnsubscribeThread(Notification notification) {
    super();
    this.notification = notification;
  }

  @Override
  protected Observable<Boolean> getApiObservable(RestAdapter restAdapter) {
    return restAdapter.create(NotificationsService.class)
        .unsubscribeThread(String.valueOf(notification.id))
        .map(new Func1<Response, Boolean>() {
          @Override
          public Boolean call(Response response) {
            return response != null && response.getStatus() == 204;
          }
        });
  }
}
