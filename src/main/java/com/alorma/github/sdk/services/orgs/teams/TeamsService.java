package com.alorma.github.sdk.services.orgs.teams;

import com.alorma.github.sdk.bean.dto.response.ListTeams;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 04/09/2014.
 */
public interface TeamsService {

	@GET("/orgs/{org}/teams")
	void teams(@Path("org") String org, Callback<ListTeams> callback);

	@GET("/orgs/{org}/teams")
	void teams(@Path("org") String org, @Query("page") int page, Callback<ListTeams> callback);
}
