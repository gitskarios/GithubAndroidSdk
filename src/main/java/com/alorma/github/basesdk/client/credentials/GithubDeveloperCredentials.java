package com.alorma.github.basesdk.client.credentials;

import com.alorma.github.basesdk.client.GithubDeveloperCredentialsProvider;

/**
 * Created by Bernat on 07/07/2015.
 */
public class GithubDeveloperCredentials {

    private static GithubDeveloperCredentials githubDeveloperCredentials;
    private GithubDeveloperCredentialsProvider provider;

    public static void init(GithubDeveloperCredentialsProvider provider) {
        getInstance().provider = provider;
    }

    public static GithubDeveloperCredentials getInstance() {
        if (githubDeveloperCredentials == null) {
            githubDeveloperCredentials = new GithubDeveloperCredentials();
        }
        return githubDeveloperCredentials;
    }

    private GithubDeveloperCredentials() {

    }

    public GithubDeveloperCredentialsProvider getProvider() {
        return provider;
    }
}
