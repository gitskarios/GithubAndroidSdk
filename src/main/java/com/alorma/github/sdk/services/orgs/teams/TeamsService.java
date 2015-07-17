package com.alorma.github.sdk.services.orgs.teams;

import com.alorma.github.sdk.bean.dto.response.Team;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 04/09/2014.
 */
public interface TeamsService {

	@GET("/orgs/{org}/teams")
	void teams(@Path("org") String org, Callback<List<Team>> callback);

	@GET("/orgs/{org}/teams")
	void teams(@Path("org") String org, @Query("page") int page, Callback<List<Team>> callback);
}
