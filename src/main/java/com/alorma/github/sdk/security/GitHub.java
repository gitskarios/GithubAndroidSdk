package com.alorma.github.sdk.security;

import com.alorma.github.sdk.BuildConfig;
import com.alorma.gitskarios.basesdk.ApiClient;

/**
 * Created by Bernat on 08/07/2014.
 */
public class GitHub implements ApiClient {
    @Override
    public String getApiClient() {
        return BuildConfig.CLIENT_ID;
    }

    @Override
    public String getAPiSecret() {
        return BuildConfig.CLIENT_SECRET;
    }

    @Override
    public String getApiOauth() {
        return BuildConfig.CLIENT_CALLBACK;
    }

    @Override
    public String getApiOauthUrlEndpoint() {
        return "https://github.com";
    }

    @Override
    public String getApiEndpoint() {
        return "https://api.github.com";
    }

    @Override
    public String getType() {
        return "github";
    }

}
