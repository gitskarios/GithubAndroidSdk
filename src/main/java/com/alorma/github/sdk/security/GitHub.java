package com.alorma.github.sdk.security;

import android.net.Uri;

import com.alorma.gitskarios.core.ApiClient;

/**
 * Created by Bernat on 08/07/2014.
 */
public class GitHub implements ApiClient {

    private String hostname;

    public GitHub() {
        if (GithubDeveloperCredentials.getInstance().getProvider() == null) {
            throw new IllegalArgumentException("Credentials provider cannot be null");
        }
    }

    public GitHub(String hostname) {
        if (GithubDeveloperCredentials.getInstance().getProvider() == null) {
            throw new IllegalArgumentException("Credentials provider cannot be null");
        }
        if (hostname != null) {
            Uri parse = Uri.parse(hostname);
            if (parse.getScheme() == null) {
                parse = parse.buildUpon().scheme("https").build();
            }
            hostname = parse.toString();
            this.hostname = hostname;
        }
    }

    @Override
    public String getApiOauthUrlEndpoint() {
        return hostname == null ? "https://github.com": hostname;
    }

    @Override
    public String getApiEndpoint() {
        String hostname = "https://api.github.com";

        if (this.hostname != null) {
            hostname = this.hostname;
            if (!hostname.endsWith("/")) {
                hostname = hostname + "/";
            }

            hostname = hostname + "api/v3/";
        }

        return hostname;
    }

    @Override
    public String getType() {
        return "github";
    }

}
