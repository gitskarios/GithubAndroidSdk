package com.alorma.github.sdk.services.pullrequest;

import com.alorma.github.sdk.bean.dto.request.IssueRequest;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.dto.response.IssueComment;
import com.alorma.github.sdk.bean.dto.response.ListEvents;
import com.alorma.github.sdk.bean.dto.response.ListIssueComments;
import com.alorma.github.sdk.bean.dto.response.ListIssues;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 22/08/2014.
 */
public interface PullRequestsService {

    @GET("/repos/{owner}/{name}/pulls?state=all&sort=created&direction=desc")
    void pulls(@Path("owner") String owner, @Path("name") String repo, Callback<ListIssues> callback);

    @GET("/repos/{owner}/{name}/pulls?state=all&sort=created&direction=desc")
    void pulls(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<ListIssues> callback);

    @GET("/repos/{owner}/{name}/pulls/{num}")
    void detail(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<Issue> callback);

    @GET("/repos/{owner}/{name}/pulls/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<ListIssueComments> callback);

    @GET("/repos/{owner}/{name}/pulls/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page, Callback<ListIssueComments> callback);

	@GET("/repos/{owner}/{name}/pulls/{num}/events")
	void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<ListEvents> callback);

	@GET("/repos/{owner}/{name}/pulls/{num}/events")
	void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page, Callback<ListEvents> callback);

	@PATCH("/repos/{owner}/{name}/pulls/{num}")
	void closeIssue(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body IssueRequest issueRequest, Callback<Issue> callback);

	@POST("/repos/{owner}/{name}/pulls/{num}/comments")
	void addComment(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body IssueComment comment, Callback<IssueComment> callback);
}
