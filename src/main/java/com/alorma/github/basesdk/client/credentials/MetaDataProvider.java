package com.alorma.github.basesdk.client.credentials;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.alorma.github.basesdk.client.GithubDeveloperCredentialsProvider;

/**
 * Created by Bernat on 07/07/2015.
 */
public class MetaDataProvider implements GithubDeveloperCredentialsProvider {

    private String API_CLIENT;
    private String API_SECRET;
    private String API_OAUTH;

    public MetaDataProvider(Context context) {
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

}
