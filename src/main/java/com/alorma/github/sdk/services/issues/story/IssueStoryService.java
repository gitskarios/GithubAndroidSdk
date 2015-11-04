package com.alorma.github.sdk.services.issues.story;

import com.alorma.github.sdk.bean.dto.request.IssueRequest;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.Label;
import com.alorma.github.sdk.bean.issue.IssueEvent;
import com.alorma.github.sdk.bean.issue.IssueStory;
import com.alorma.github.sdk.bean.issue.ListIssueEvents;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Bernat on 22/08/2014.
 */
public interface IssueStoryService {

    //Async
    @POST("/repos/{owner}/{name}/issues")
    void create(@Path("owner") String owner, @Path("name") String repo, @Body IssueRequest issue, Callback<IssueStory> callback);

    @GET("/repos/{owner}/{name}/issues/{num}")
    void detail(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<Issue> issueCallback);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<List<GithubComment>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page, Callback<List<GithubComment>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<List<IssueEvent>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page, Callback<List<IssueEvent>> callback);

    @PATCH("/repos/{owner}/{name}/issues/{num}")
    void closeIssue(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body IssueRequest issueRequest, Callback<Issue> callback);

    @POST("/repos/{owner}/{name}/issues/{num}/comments")
    void addComment(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body GithubComment comment, Callback<GithubComment> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/labels")
    void labels(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, Callback<List<Label>> issueCallback);

    @GET("/repos/{owner}/{name}/issues/{num}/labels")
    void labels(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page, Callback<List<Label>> issueCallback);


    //Sync
    @POST("/repos/{owner}/{name}/issues")
    IssueStory create(@Path("owner") String owner, @Path("name") String repo, @Body IssueRequest issue);

    @GET("/repos/{owner}/{name}/issues/{num}")
    Issue detail(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    List<GithubComment> comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    List<GithubComment> comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    List<IssueEvent> events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    List<IssueEvent> events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page);

    @PATCH("/repos/{owner}/{name}/issues/{num}")
    Issue closeIssue(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body IssueRequest issueRequest);

    @POST("/repos/{owner}/{name}/issues/{num}/comments")
    GithubComment addComment(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Body GithubComment comment);

    @GET("/repos/{owner}/{name}/issues/{num}/labels")
    List<Label> labels(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/labels")
    List<Label> labels(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page);


    // Observable

    @GET("/repos/{owner}/{name}/issues/{num}")
    Observable<Issue> detailObs(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    Observable<List<GithubComment>> commentsObs(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    Observable<List<GithubComment>> commentsObs(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num, @Query("page") int page);


}
