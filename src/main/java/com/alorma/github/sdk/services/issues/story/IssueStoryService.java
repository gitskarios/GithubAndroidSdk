package com.alorma.github.sdk.services.issues.story;

import com.alorma.github.sdk.bean.dto.request.IssueRequest;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.ListIssueComments;
import com.alorma.github.sdk.bean.issue.IssueStory;
import com.alorma.github.sdk.bean.issue.ListIssueEvents;
import com.alorma.github.sdk.bean.issue.ListIssueLabels;

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
public interface IssueStoryService {

    @POST("/repos/{owner}/{name}/issues")
    void create(@Path("owner") String owner, @Path("name") String repo, @Body IssueRequest issue, Callback<IssueStory> callback);

    @GET("/repos/{owner}/{name}/issues/{num}")
    void detail(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<Issue> issueCallback);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<ListIssueComments> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page, Callback<ListIssueComments> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<ListIssueEvents> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page, Callback<ListIssueEvents> callback);

    @PATCH("/repos/{owner}/{name}/issues/{num}")
    void closeIssue(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body IssueRequest issueRequest, Callback<Issue> callback);

    @POST("/repos/{owner}/{name}/issues/{num}/comments")
    void addComment(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body GithubComment comment, Callback<GithubComment> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/labels")
    void labels(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<ListIssueLabels> issueCallback);

    @GET("/repos/{owner}/{name}/issues/{num}/labels")
    void labels(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page, Callback<ListIssueLabels> issueCallback);


}
