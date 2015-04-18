package com.alorma.github.sdk.services.client;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.info.PaginationLink;
import com.alorma.github.sdk.bean.info.RelType;
import com.alorma.github.sdk.security.ApiConstants;
import com.alorma.github.sdk.security.StoreCredentials;
import com.alorma.github.sdk.security.UnAuthIntent;
import com.alorma.github.sdk.services.issues.story.IssueStoryService;

import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.converter.Converter;

public abstract class BaseClient<K> implements Callback<K>, RequestInterceptor, RestAdapter.Log {

	private final StoreCredentials storeCredentials;

	protected final Context context;
	private OnResultCallback<K> onResultCallback;
	protected Handler handler;

	public BaseClient(Context context) {
		this.context = context.getApplicationContext();
		storeCredentials = new StoreCredentials(context);
		handler = new Handler();
	}

	public void execute() {
		RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
				.setClient(new OkClient())
				.setEndpoint(ApiConstants.API_URL)
				.setRequestInterceptor(this)
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setLog(this);

		if (customConverter() != null) {
			restAdapterBuilder.setConverter(customConverter());
		}

		executeService(restAdapterBuilder.build());
	}

	protected Converter customConverter() {
		return null;
	}

	protected abstract void executeService(RestAdapter restAdapter);

	@Override
	public void success(final K k, final Response response) {
		if (handler != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					sendResponse(k, response);
				}
			});
		} else {
			sendResponse(k, response);
		}
	}

	private void sendResponse(K k, Response response) {
		if (onResultCallback != null) {
			onResultCallback.onResponseOk(k, response);
		}
	}

	@Override
	public void failure(final RetrofitError error) {
		if (handler != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					sendError(error);
				}
			});
		} else {
			sendError(error);
		}
	}

	private void sendError(RetrofitError error) {
		if (error.getResponse() != null && error.getResponse().getStatus() == 401) {
			LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
			manager.sendBroadcast(new UnAuthIntent(storeCredentials.token()));
		} else {
			if (onResultCallback != null) {
				onResultCallback.onFail(error);
			}
		}
	}

	public OnResultCallback<K> getOnResultCallback() {
		return onResultCallback;
	}

	public void setOnResultCallback(OnResultCallback<K> onResultCallback) {
		this.onResultCallback = onResultCallback;
	}

	@Override
	public void intercept(RequestFacade request) {
		request.addHeader("Accept", getAcceptHeader());
		request.addHeader("Authorization", "token " + getToken());
	}

	protected String getToken() {
		return storeCredentials.token();
	}

	@Override
	public void log(String message) {
		Log.v("RETROFIT_LOG", message);
	}

	public String getAcceptHeader() {
		return "application/vnd.github.moondragon+json";
	}

	public Context getContext() {
		return context;
	}
	
	public interface OnResultCallback<K> {
		void onResponseOk(K k, Response r);

		void onFail(RetrofitError error);
	}

	public abstract class BaseInfiniteCallback<T> implements Callback<T> {


		public BaseInfiniteCallback() {

		}

		@Override
		public void success(T t, Response response) {
			int nextPage = getLinkData(response);
			response(t);
			if (nextPage != -1) {
				executePaginated(nextPage);
			} else {
				executeNext();
			}
		}

		protected abstract void executePaginated(int nextPage);

		protected abstract void executeNext();

		protected abstract void response(T t);

		private int getLinkData(Response r) {
			List<Header> headers = r.getHeaders();
			Map<String, String> headersMap = new HashMap<String, String>(headers.size());
			for (Header header : headers) {
				headersMap.put(header.getName(), header.getValue());
			}

			String link = headersMap.get("Link");

			if (link != null) {
				String[] parts = link.split(",");
				try {
					PaginationLink bottomPaginationLink = new PaginationLink(parts[0]);
					if (bottomPaginationLink.rel == RelType.next) {
						return bottomPaginationLink.page;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return -1;
		}

		public abstract void execute();

		@Override
		public void failure(RetrofitError error) {
			if (getOnResultCallback() != null) {
				getOnResultCallback().onFail(error);
			}
		}

	}
}
