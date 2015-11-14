package com.alorma.github.sdk.services.client;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alorma.gitskarios.core.ApiClient;
import com.alorma.gitskarios.core.client.BaseClient;
import com.alorma.gitskarios.core.client.PaginationLink;
import com.alorma.gitskarios.core.client.RelType;

import com.alorma.github.sdk.security.GitHub;
import com.alorma.github.sdk.security.InterceptingOkClient;
import com.alorma.gitskarios.core.client.StoreCredentials;
import com.squareup.okhttp.OkHttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

public abstract class GithubClient<K>  extends BaseClient<K> {

	public GithubClient(Context context) {
		super(context, getApiClient(context));
	}

	private static ApiClient getApiClient(Context context) {
		String url = new StoreCredentials(context).getUrl();
		return new GitHub(url);
	}

	@Override
	public void intercept(RequestFacade request) {
		request.addHeader("Accept", getAcceptHeader());
		request.addHeader("User-Agent", "Gitskarios");
		request.addHeader("Authorization", "token " + getToken());
	}

	@Override
	public void log(String message) {
		Log.v("RETROFIT_LOG", message);
	}

	public String getAcceptHeader() {
		return "application/vnd.github.v3.json";
	}

	@Nullable
	@Override
	protected InterceptingOkClient getInterceptor() {
		return new InterceptingOkClient(new OkHttpClient(), this);
	}
}
