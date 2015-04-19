package com.alorma.github.sdk.services.user;

import android.content.Context;

import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Bernat on 13/07/2014.
 */
public abstract class GithubUsersClient<K> extends GithubClient<K> {
    public GithubUsersClient(Context context) {
        super(context);
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        UsersService usersService = restAdapter.create(UsersService.class);
        executeService(usersService);
    }

    protected abstract void executeService(UsersService usersService);

}
