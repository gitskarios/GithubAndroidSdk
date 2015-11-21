package com.alorma.github.sdk.services.client;

import android.content.Context;
import android.util.Log;
import com.alorma.github.sdk.security.GitHub;
import com.alorma.gitskarios.core.ApiClient;
import com.alorma.gitskarios.core.client.BaseClient;
import com.alorma.gitskarios.core.client.StoreCredentials;

public abstract class GithubClient<K> extends BaseClient<K> {

  public GithubClient(Context context) {
    super(context, getApiClient(context));
  }

  private static ApiClient getApiClient(Context context) {
    String url = new StoreCredentials(context).getUrl();
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
}
