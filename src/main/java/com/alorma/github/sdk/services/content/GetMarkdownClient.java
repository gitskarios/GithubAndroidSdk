package com.alorma.github.sdk.services.content;

import android.content.Context;
import android.os.Handler;

import com.alorma.github.basesdk.client.StoreCredentials;
import com.alorma.github.sdk.bean.dto.request.RequestMarkdownDTO;
import com.alorma.github.sdk.security.GitHub;
import com.alorma.github.sdk.services.client.GithubClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.Scanner;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * Created by Bernat on 22/07/2014.
 */
public class GetMarkdownClient extends GithubClient<String> {

	private RequestMarkdownDTO readme;

    public GetMarkdownClient(Context context, RequestMarkdownDTO readme, Handler handler) {
        super(context);
		this.readme = readme;
		this.handler = handler;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(ContentService.class).markdown(readme, this);
    }

    @Override
    protected String executeServiceSync(RestAdapter restAdapter) {
        return restAdapter.create(ContentService.class).markdown(readme);
    }
}
