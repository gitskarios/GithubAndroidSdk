package com.alorma.github.sdk.services.gists;

import com.alorma.github.sdk.bean.dto.response.Gist;

import java.util.List;

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

	//Async
	@GET("/gists")
	void userGistsList(Callback<List<Gist>> callback);

	@GET("/gists")
	void userGistsList(@Query("page") int page, Callback<List<Gist>> callback);

	@GET("/users/{username}/gists")
	void userGistsList(@Path("username") String username, Callback<List<Gist>> callback);

	@GET("/users/{username}/gists")
	void userGistsList(@Path("username") String username, @Query("page") int page, Callback<List<Gist>> callback);

	@GET("/gists/starred")
	void userStarredGistsList(Callback<List<Gist>> callback);

	@GET("/gists/starred")
	void userStarredGistsList(@Query("page") int page, Callback<List<Gist>> callback);

	@GET("/gists/{id}")
	void gistDetail(@Path("id") String id, Callback<Gist> callback);

	@POST("/gists")
	void publish(@Body Gist gist, Callback<Gist> gistCallback);


	//Sync
	@GET("/gists")
	List<Gist> userGistsList();

	@GET("/gists")
	List<Gist> userGistsList(@Query("page") int page);

	@GET("/users/{username}/gists")
	List<Gist> userGistsList(@Path("username") String username);

	@GET("/users/{username}/gists")
	List<Gist> userGistsList(@Path("username") String username, @Query("page") int page);

	@GET("/gists/starred")
	List<Gist> userStarredGistsList();

	@GET("/gists/starred")
	List<Gist> userStarredGistsList(@Query("page") int page);

	@GET("/gists/{id}")
	Gist gistDetail(@Path("id") String id);

	@POST("/gists")
	Gist publish(@Body Gist gist);
}