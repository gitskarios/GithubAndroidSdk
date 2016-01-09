package com.alorma.github.sdk.services.user;

import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 03/10/2014.
 */
public class GetAuthUserClient extends GithubClient<User> {
    private String accessToken;

    public GetAuthUserClient() {
        super();
    }

    public GetAuthUserClient(String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    @Override
    protected Observable<User> getApiObservable(RestAdapter restAdapter) {
        return restAdapter.create(UsersService.class).me();
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
