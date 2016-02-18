package com.alorma.github.sdk.services.user;

import android.util.Base64;
import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.services.client.GithubClient;
import java.util.List;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;
import rx.Observable;

public class GetAuthUserClient extends GithubClient<User> {
  private String username;
  private String password;
  private String accessToken;

  public GetAuthUserClient() {
    super();
  }

  public GetAuthUserClient(String accessToken) {
    super();
    this.accessToken = accessToken;
  }

  public GetAuthUserClient(String username, String password) {
    super();
    this.username = username;
    this.password = password;
  }

  @Override
  protected Observable<User> getApiObservable(RestAdapter restAdapter) {
    return restAdapter.create(UsersService.class).me().onErrorResumeNext(throwable -> {
      if (throwable instanceof RetrofitError) {
        Response response = ((RetrofitError) throwable).getResponse();
        if (response != null && response.getStatus() == 401) {
          List<Header> headers = response.getHeaders();
          if (headers != null) {
            for (Header header : headers) {
              if (header.getName().equals("X-GitHub-OTP") && header.getValue()
                  .contains("required")) {
                return Observable.error(new TwoFactorAuthException());
              }
            }
            return Observable.error(new UnauthorizedException());
          }
        }
      }
      return Observable.error(throwable);
    });
  }

  @Override
  public void intercept(RequestFacade request) {
    super.intercept(request);

    if (username != null && password != null) {
      String userCredentials = username + ":" + password;
      String basicAuth =
          "Basic " + new String(Base64.encode(userCredentials.getBytes(), Base64.DEFAULT));

      request.addHeader("Authorization", basicAuth);
    }
  }

  @Override
  protected String getToken() {
    if (accessToken != null) {
      return accessToken;
    } else {
      return super.getToken();
    }
  }
}
