package com.alorma.github.sdk.services.login;

import android.content.Context;

import com.alorma.github.sdk.security.GithubDeveloperCredentials;
import com.alorma.github.sdk.bean.dto.request.RequestTokenDTO;
import com.alorma.github.sdk.bean.dto.response.Token;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 13/07/2014.
 */
public class RequestTokenClient extends GithubClient<Token> {
    private String code;

    public RequestTokenClient(Context context, String code) {
        super(context);
        this.code = code;
    }

    @Override
    protected RestAdapter getRestAdapter() {
        RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(getClient().getApiOauthUrlEndpoint())
            .setRequestInterceptor(this)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

        return restAdapter;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Accept", "application/json");
    }

    @Override
    protected Observable<Token> getApiObservable(RestAdapter restAdapter) {
        LoginService loginService = restAdapter.create(LoginService.class);


        RequestTokenDTO tokenDTO = new RequestTokenDTO();
        tokenDTO.client_id = GithubDeveloperCredentials.getInstance().getProvider().getApiClient();
        tokenDTO.client_secret = GithubDeveloperCredentials.getInstance().getProvider().getAPiSecret();
        tokenDTO.redirect_uri = GithubDeveloperCredentials.getInstance().getProvider().getApiOauth();
        tokenDTO.code = code;

        return loginService.requestToken(tokenDTO);
    }
}
