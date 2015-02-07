package com.alorma.github.sdk.services.repo.actions;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Bernat on 07/08/2014.
 */
public interface ActionsService {

    @GET("/user/starred/{owner}/{name}")
    void checkIfRepoIsStarred(@Path("owner") String owner, @Path("name") String repo, Callback<Object> callback);

    @Headers("Content-Length: 0")
    @PUT("/user/starred/{owner}/{name}")
    void starRepo(@Path("owner") String owner, @Path("name") String repo, Callback<Object> callback);

    @DELETE("/user/starred/{owner}/{name}")
    void unstarRepo(@Path("owner") String owner, @Path("name") String repo, Callback<Object> callback);

    @GET("/user/subscriptions/{owner}/{name}")
    void checkIfRepoIsWatched(@Path("owner") String owner, @Path("name") String repo, Callback<Object> callback);

    @Headers("Content-Length: 0")
    @PUT("/user/subscriptions/{owner}/{name}")
    void watchRepo(@Path("owner") String owner, @Path("name") String repo, Callback<Object> callback);

    @DELETE("/user/subscriptions/{owner}/{name}")
    void unwatchRepo(@Path("owner") String owner, @Path("name") String repo, Callback<Object> callback);

}
