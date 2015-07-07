package com.alorma.github.basesdk.client;

/**
 * Created by Bernat on 07/07/2015.
 */
public interface GithubDeveloperCredentialsProvider {
    String getApiClient();
    String getAPiSecret();
    String getApiOauth();
}
