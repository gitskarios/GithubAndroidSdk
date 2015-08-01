package com.alorma.github.sdk.services.repo;

import com.alorma.github.sdk.bean.dto.request.RepoRequestDTO;
import com.alorma.github.sdk.bean.dto.response.Branch;
import com.alorma.github.sdk.bean.dto.response.CompareCommit;
import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.dto.response.Contributor;
import com.alorma.github.sdk.bean.dto.response.Release;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.User;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PATCH;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 17/07/2014.
 */
public interface RepoService {

    @GET("/repos/{owner}/{name}")
    void get(@Path("owner") String owner, @Path("name") String repo, Callback<Repo> callback);

    @GET("/repos/{owner}/{name}/branches")
    void branches(@Path("owner") String owner, @Path("name") String repo, Callback<List<Branch>> callback);

    @GET("/repos/{owner}/{name}/contents")
    void contents(@Path("owner") String owner, @Path("name") String repo, Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/contents")
    void contentsByRef(@Path("owner") String owner, @Path("name") String repo, @Query("ref") String ref, Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/readme")
    void readme(@Path("owner") String owner, @Path("name") String repo, Callback<Content> callback);

    @GET("/repos/{owner}/{name}/readme")
    void readme(@Path("owner") String owner, @Path("name") String repo, @Query("ref") String ref, Callback<Content> callback);

    @GET("/repos/{owner}/{name}/contents/{path}")
    void contents(@Path("owner") String owner, @Path("name") String repo, @Path("path") String path, Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/contents/{path}")
    void contentsByRef(@Path("owner") String owner, @Path("name") String repo, @Path("path") String path, @Query("ref") String ref,  Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/stats/contributors")
    void contributors(@Path("owner") String owner, @Path("name") String repo, Callback<List<Contributor>> callback);

    @GET("/repos/{owner}/{name}/stats/contributors")
    void contributors(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<Contributor>> callback);

    @GET("/repos/{owner}/{name}/collaborators")
    void collaborators(@Path("owner") String owner, @Path("name") String repo, Callback<List<User>> callback);

    @GET("/repos/{owner}/{name}/collaborators")
    void collaborators(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<User>> callback);

    @GET("/repos/{owner}/{name}/releases")
    void releases(@Path("owner") String owner, @Path("name") String repo, Callback<List<Release>> callback);

    @GET("/repos/{owner}/{name}/releases")
    void releases(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page, Callback<List<Release>> callback);

    @GET("/repos/{owner}/{name}/releases/latest")
    void lastRelease(@Path("owner") String owner, @Path("name") String repo, Callback<Release> callback);

    @GET("/repos/{owner}/{name}/compare/{base}...{head}")
    void compareCommits(@Path("owner") String owner, @Path("name") String repo, @Path("base") String base, @Path("head") String head, Callback<CompareCommit> callback);

    @DELETE("/repos/{owner}/{name}")
    void delete(@Path("owner") String owner, @Path("name") String repo, Callback<Response> callback);

    @PATCH("/repos/{owner}/{name}")
    void edit(@Path("owner") String owner, @Path("name") String repo, @Body RepoRequestDTO repoRequestDTO, Callback<Repo> callback);
}
