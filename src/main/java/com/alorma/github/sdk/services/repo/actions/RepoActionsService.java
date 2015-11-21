package com.alorma.github.sdk.services.repo.actions;

import com.alorma.github.sdk.bean.dto.response.Repo;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Bernat on 07/08/2014.
 */
public interface RepoActionsService {

  @Headers("Content-Length: 0")
  @PUT("/user/starred/{owner}/{name}")
  Observable<Response> starRepo(@Path("owner") String owner, @Path("name") String repo, @Body String empty);

  @Headers("Content-Length: 0")
  @PUT("/user/subscriptions/{owner}/{name}")
  Observable<Response> watchRepo(@Path("owner") String owner, @Path("name") String repo, @Body String empty);

  @GET("/user/starred/{owner}/{name}")
  Observable<Response> checkIfRepoIsStarred(@Path("owner") String owner, @Path("name") String repo);

  @DELETE("/user/starred/{owner}/{name}")
  Observable<Response> unstarRepo(@Path("owner") String owner, @Path("name") String repo);

  @GET("/user/subscriptions/{owner}/{name}")
  Observable<Response> checkIfRepoIsWatched(@Path("owner") String owner, @Path("name") String repo);

  @DELETE("/user/subscriptions/{owner}/{name}")
  Observable<Response> unwatchRepo(@Path("owner") String owner, @Path("name") String repo);

  @Headers("Content-Length: 0")
  @POST("/repos/{owner}/{name}/forks")
  Observable<Repo> forkRepo(@Path("owner") String owner, @Path("name") String repo, @Body Object empty);

  @Headers("Content-Length: 0")
  @POST("/repos/{owner}/{name}/forks")
  Observable<Repo> forkRepo(@Path("owner") String owner, @Path("name") String repo, @Query("organization") String org, @Body Object empty);
}
