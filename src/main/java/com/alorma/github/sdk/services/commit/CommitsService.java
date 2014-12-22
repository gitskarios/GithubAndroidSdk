package com.alorma.github.sdk.services.commit;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.ListCommit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 07/09/2014.
 */
public interface CommitsService {

	@GET("/repos/{owner}/{repo}/commits")
	void commits(@Path("owner") String owner, @Path("repo") String repo, Callback<ListCommit> callback);

	@GET("/repos/{owner}/{repo}/commits/{sha}")
	void singleCommit(@Path("owner") String owner, @Path("repo") String repo, @Path("sha") String sha, Callback<Commit> callback);

	@GET("/repos/{owner}/{repo}/commits")
	void commits(@Path("owner") String owner, @Path("repo") String repo, @Query("page") int page, Callback<ListCommit> callback);

	@GET("/repos/{owner}/{repo}/commits")
	void commits(@Path("owner") String owner, @Path("repo") String repo, @Query("sha") String sha, Callback<ListCommit> callback);

	@GET("/repos/{owner}/{repo}/commits")
	void commits(@Path("owner") String owner, @Path("repo") String repo, @Query("page") int page, @Query("sha") String sha, Callback<ListCommit> callback);

}
