package com.alorma.gitskarios.core.client;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Pair;
import com.alorma.gitskarios.core.ApiClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.converter.Converter;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public abstract class BaseListClient<K> implements RequestInterceptor, RestAdapter.Log {

  public Uri last;
  public Uri next;
  public int lastPage;
  public int nextPage;
  protected StoreCredentials storeCredentials;
  protected Context context;
  private ApiClient client;

  public BaseListClient(Context context, ApiClient client) {
    this.client = client;
    if (context != null) {
      this.context = context.getApplicationContext();
    }
    storeCredentials = new StoreCredentials(context);
  }

  protected RestAdapter getRestAdapter() {
    RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder().setEndpoint(client.getApiEndpoint())
        .setRequestInterceptor(this)
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .setLog(this);

    if (customConverter() != null) {
      restAdapterBuilder.setConverter(customConverter());
    }

    if (getInterceptor() != null) {
      restAdapterBuilder.setClient(getInterceptor());
    }

    return restAdapterBuilder.build();
  }

  @Nullable
  protected Client getInterceptor() {
    return null;
  }

  public Observable<? extends Pair<K, Integer>> observable() {
    return getApiObservable().subscribeOn(Schedulers.io());
  }

  private Observable<? extends Pair<K, Integer>> getApiObservable() {
    return Observable.create(getApiObservable(getRestAdapter()));
  }

  protected abstract ApiSubscriber getApiObservable(RestAdapter restAdapter);

  protected Converter customConverter() {
    return null;
  }

  protected String getToken() {
    return storeCredentials.token();
  }

  public Context getContext() {
    return context;
  }

  public ApiClient getClient() {
    return client;
  }

  public void setStoreCredentials(StoreCredentials storeCredentials) {
    this.storeCredentials = storeCredentials;
  }

  public abstract class ApiSubscriber implements Observable.OnSubscribe<Pair<K, Integer>>, Callback<K> {

    Subscriber<? super Pair<K, Integer>> subscriber;

    public ApiSubscriber() {
    }

    @Override
    public void success(K k, Response r) {
      subscriber.onNext(new Pair<>(k, getLinkData(r)));
      subscriber.onCompleted();
    }

    @Override
    public void failure(RetrofitError error) {
      if (error.getResponse() != null && error.getResponse().getStatus() == 401) {
        if (context != null) {
          LocalBroadcastManager manager = LocalBroadcastManager.getInstance(context);
          manager.sendBroadcast(new UnAuthIntent(storeCredentials.token()));
        }
      } else {
        subscriber.onError(error);
      }
    }

    private Integer getLinkData(Response r) {
      if (r != null) {
        List<Header> headers = r.getHeaders();
        Map<String, String> headersMap = new HashMap<String, String>(headers.size());
        for (Header header : headers) {
          headersMap.put(header.getName(), header.getValue());
        }

        String link = headersMap.get("Link");

        if (link != null) {
          String[] parts = link.split(",");
          try {
            PaginationLink paginationLink = new PaginationLink(parts[0]);
            return paginationLink.rel == RelType.next ? paginationLink.page : null;
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
      return null;
    }

    @Override
    public void call(Subscriber<? super Pair<K, Integer>> subscriber) {
      this.subscriber = subscriber;
      call(getRestAdapter());
    }

    protected abstract void call(RestAdapter restAdapter);
  }
}
