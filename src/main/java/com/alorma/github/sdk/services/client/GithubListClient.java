package com.alorma.github.sdk.services.client;

import android.util.Log;

import com.alorma.github.sdk.security.GitHub;
import com.alorma.github.sdk.security.InterceptingListOkClient;
import com.alorma.gitskarios.core.ApiClient;
import com.alorma.gitskarios.core.client.BaseListClient;
import com.alorma.gitskarios.core.client.UrlProvider;
import com.squareup.okhttp.OkHttpClient;

public abstract class GithubListClient<K> extends BaseListClient<K> {

  public GithubListClient() {
    super(getApiClient());
  }

  private static ApiClient getApiClient() {
    String url = UrlProvider.getInstance().getUrl();
    return new GitHub(url);
  }

  @Override
  public void intercept(RequestFacade request) {
    request.addHeader("Accept", getAcceptHeader());
    request.addHeader("User-Agent", "Gitskarios");
    request.addHeader("Authorization", "token " + getToken());
  }

  @Override
  public void log(String message) {
    Log.v("RETROFIT_LOG", message);
  }

  public String getAcceptHeader() {
    return "application/vnd.github.v3.json";
  }

  @Override
  protected InterceptingListOkClient getInterceptor() {
    return new InterceptingListOkClient(new OkHttpClient(), this);
  }
}
