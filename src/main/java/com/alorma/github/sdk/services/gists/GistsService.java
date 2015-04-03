package com.alorma.github.sdk.services.gists;

import com.alorma.github.sdk.bean.dto.response.Gist;
import com.alorma.github.sdk.bean.dto.response.ListGists;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 08/07/2014.
 */
public interface GistsService {

	@GET("/gists")
	void userGistsList(Callback<ListGists> callback);

	@GET("/gists")
	void userGistsList(@Query("page") int page, Callback<ListGists> callback);

	@GET("/users/{username}/gists")
	void userGistsList(@Path("username") String username, Callback<ListGists> callback);

	@GET("/users/{username}/gists")
	void userGistsList(@Path("username") String username, @Query("page") int page, Callback<ListGists> callback);

	@GET("/gists/starred")
	void userStarredGistsList(Callback<ListGists> callback);

	@GET("/gists/starred")
	void userStarredGistsList(@Query("page") int page, Callback<ListGists> callback);

	@GET("/gists/{id}")
	void gistDetail(@Path("id") String id, Callback<Gist> callback);

	@POST("/gists")
	void publish(@Body Gist gist, Callback<Gist> gistCallback);
}