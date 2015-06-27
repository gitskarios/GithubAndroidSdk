package com.alorma.github.sdk.security;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.alorma.github.sdk.BuildConfig;
import com.alorma.gitskarios.basesdk.ApiClient;

import java.util.Collection;

/**
 * Created by Bernat on 08/07/2014.
 */
public class GitHub implements ApiClient {

    private String API_CLIENT;
    private String API_SECRET;
    private String API_OAUTH;

    public GitHub(Context context){
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            API_CLIENT = bundle.getString("com.alorma.github.sdk.client");
            API_SECRET = bundle.getString("com.alorma.github.sdk.secret");
            API_OAUTH = bundle.getString("com.alorma.github.sdk.oauth");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("GitHubSdk", "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e("GitHubSdk", "Failed to load meta-data, NullPointer: " + e.getMessage());
        }
    }

    @Override
    public String getApiClient() {
        return API_CLIENT;
    }

    @Override
    public String getAPiSecret() {
        return API_SECRET;
    }

    @Override
    public String getApiOauth() {
        return API_OAUTH;
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
