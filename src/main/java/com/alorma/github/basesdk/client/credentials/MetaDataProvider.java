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

    private String apiClient;
    private String apiSecret;
    private String apiOauth;

    public MetaDataProvider(Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            apiClient = bundle.getString("com.alorma.github.sdk.client");
            apiSecret = bundle.getString("com.alorma.github.sdk.secret");
            apiOauth = bundle.getString("com.alorma.github.sdk.oauth");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("GitHubSdk", "Failed to load meta-data, NameNotFound: " + e.getMessage());
        } catch (NullPointerException e) {
            Log.e("GitHubSdk", "Failed to load meta-data, NullPointer: " + e.getMessage());
        }
    }

    @Override
    public String getApiClient() {
        return apiClient;
    }

    @Override
    public String getAPiSecret() {
        return apiSecret;
    }

    @Override
    public String getApiOauth() {
        return apiOauth;
    }

}
