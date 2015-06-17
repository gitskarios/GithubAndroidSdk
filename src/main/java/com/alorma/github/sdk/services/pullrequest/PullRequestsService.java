package com.alorma.github.sdk.services.pullrequest;

import com.alorma.github.sdk.bean.dto.request.IssueRequest;
import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.CommitFile;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.ListEvents;
import com.alorma.github.sdk.bean.dto.response.ListIssueComments;
import com.alorma.github.sdk.bean.dto.response.ListIssues;

import java.util.List;

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

    @GET("/repos/{owner}/{repo}/pulls/{number}/commits")
    void commits(@Path("owner") String owner,@Path("repo") String repo, @Path("number") int number, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{repo}/pulls/{number}/commits")
    void commits(@Path("owner") String owner,@Path("repo") String repo, @Path("number") int number, @Query("page") int page, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{repo}/pulls/{number}/files")
    void files(@Path("owner") String owner,@Path("repo") String repo, @Path("number") int number, Callback<List<CommitFile>> callback);

    @GET("/repos/{owner}/{repo}/pulls/{number}/files")
    void files(@Path("owner") String owner,@Path("repo") String repo, @Path("number") int number, @Query("page") int page, Callback<List<CommitFile>> callback);

}
