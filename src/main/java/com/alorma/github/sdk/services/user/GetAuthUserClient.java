package com.alorma.github.sdk.services.user;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.utils.GitskariosSettings;
import com.google.gson.Gson;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 03/10/2014.
 */
public class GetAuthUserClient extends BaseUsersClient<User> {
    private String accessToken;

    public GetAuthUserClient(Context context) {
        super(context);
    }
    public GetAuthUserClient(Context context, String accessToken) {
        super(context);
        this.accessToken = accessToken;
    }

    @Override
    protected void executeService(UsersService usersService) {
        usersService.me(this);
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
