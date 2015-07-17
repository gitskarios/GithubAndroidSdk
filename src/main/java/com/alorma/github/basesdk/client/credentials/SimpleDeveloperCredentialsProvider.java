package com.alorma.github.basesdk.client.credentials;

import com.alorma.github.basesdk.client.GithubDeveloperCredentialsProvider;

/**
 * Created by Bernat on 07/07/2015.
 */
public class SimpleDeveloperCredentialsProvider implements GithubDeveloperCredentialsProvider {

    private String apiClient;
    private String apiSecret;
    private String apiOauth;

    public SimpleDeveloperCredentialsProvider(String apiClient, String apiSecret, String apiOauth) {
        this.apiClient = apiClient;
        this.apiSecret = apiSecret;
        this.apiOauth = apiOauth;
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
