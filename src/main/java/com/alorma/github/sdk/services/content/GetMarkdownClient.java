package com.alorma.github.sdk.services.content;

import android.content.Context;
import android.os.Handler;

import com.alorma.github.sdk.bean.dto.request.RequestMarkdownDTO;
import com.alorma.github.sdk.security.GitHub;
import com.alorma.gitskarios.basesdk.client.StoreCredentials;
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
public class GetMarkdownClient implements Callback<String>, Client {

	private final Handler handler;
	private Context context;
	private RequestMarkdownDTO readme;

    private GithubClient.OnResultCallback<String> onResultCallback;

    public GetMarkdownClient(Context context, RequestMarkdownDTO readme, Handler handler) {
		this.context = context;
		this.readme = readme;
		this.handler = handler;
    }

    public void execute() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(new GitHub(context).getApiEndpoint())
                .setLogLevel(RestAdapter.LogLevel.HEADERS)
                .setClient(this)
                .build();

        executeService(restAdapter);
    }

    private void executeService(RestAdapter restAdapter) {
        restAdapter.create(ContentService.class).markdown(readme, this);
    }

    @Override
    public void success(String s, Response response) {

    }

    @Override
    public void failure(RetrofitError error) {

    }

    @Override
    public Response execute(Request request) throws IOException {


		StoreCredentials storeCredentials = new StoreCredentials(context);
		
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(request.getUrl());
        httppost.setHeader("Accept", "application/json");
        httppost.setHeader("Content-type", "text/plain");
		httppost.setHeader("Authorization", "token " + storeCredentials.token());
        httppost.setEntity(new StringEntity(readme.text, "UTF-8"));

        HttpResponse response = httpclient.execute(httppost);

        final String inputStreamString = new Scanner(response.getEntity().getContent(),"UTF-8").useDelimiter("\\A").next();

		handler.post(new Runnable() {
			@Override
			public void run() {
				if (onResultCallback != null) {
					onResultCallback.onResponseOk(inputStreamString, null);
				}
			}
		});

        return null;
    }

	
    public void setOnResultCallback(GithubClient.OnResultCallback<String> onResultCallback) {
        this.onResultCallback = onResultCallback;
    }
}
