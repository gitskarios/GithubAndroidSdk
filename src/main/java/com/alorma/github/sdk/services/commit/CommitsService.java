package com.alorma.github.sdk.services.commit;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.CommitComment;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 07/09/2014.
 */
public interface CommitsService {

    @GET("/repos/{owner}/{name}/commits/{sha}")
    void singleCommit(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha, Callback<Commit> callback);

    @GET("/repos/{owner}/{name}/commits/{sha}/comments")
    void singleCommitComments(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha, Callback<List<CommitComment>> callback);

    @GET("/repos/{owner}/{name}/commits/{sha}/comments")
    void singleCommitComments(@Path("owner") String owner, @Path("name") String repo, @Path("sha") String sha, @Query("page") int page, Callback<List<CommitComment>> callback);


    @GET("/repos/{owner}/{name}/commits")
    void commits(@Path("owner") String owner, @Path("name") String repo, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commits(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commits(@Path("owner") String owner, @Path("name") String repo, @Query("sha") String sha, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commits(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, @Query("sha") String sha, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, @Query("page") int page, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, @Query("sha") String sha, Callback<List<Commit>> callback);

    @GET("/repos/{owner}/{name}/commits")
    void commitsByPath(@Path("owner") String owner, @Path("name") String repo, @Query("path") String path, @Query("sha") String sha, @Query("page") int page, Callback<List<Commit>> callback);

}
