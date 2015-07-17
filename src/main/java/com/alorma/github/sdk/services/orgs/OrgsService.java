package com.alorma.github.sdk.services.orgs;

import com.alorma.github.sdk.bean.dto.response.Organization;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 04/09/2014.
 */
public interface OrgsService {

	@GET("/user/orgs")
	void orgs(Callback<List<Organization>> callback);

	@GET("/users/{username}/orgs")
	void orgsByUser(@Path("username") String username, Callback<List<Organization>> callback);

	@GET("/user/orgs")
	void orgs(@Query("page") int page, Callback<List<Organization>> callback);

	@GET("/users/{username}/orgs")
	void orgsByUser(@Path("username") String username, @Query("page") int page, Callback<List<Organization>> callback);

}
