package com.alorma.github.sdk.services.notifications;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.Notification;
import com.alorma.github.sdk.services.client.GithubClient;
import java.util.List;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 18/02/2015.
 */
public class GetNotificationsClient extends GithubClient<List<Notification>> {

  private String token;
  private boolean participating = false;
  private boolean all = false;

  public GetNotificationsClient(Context context) {
    super(context);
  }

  public GetNotificationsClient(Context context, String token) {
    super(context);
    this.token = token;
  }

  public void setParticipating(boolean participating) {
    this.participating = participating;
  }

  public void setAll(boolean all) {
    this.all = all;
  }

  @Override
  public String getToken() {
    return token != null ? token : super.getToken();
  }

  @Override
  protected Observable<List<Notification>> getApiObservable(RestAdapter restAdapter) {
    return restAdapter.create(NotificationsService.class).getNotifications(all, participating);
  }
}
