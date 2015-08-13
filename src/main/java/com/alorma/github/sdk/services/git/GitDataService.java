package com.alorma.github.sdk.services.git;

import com.alorma.github.sdk.bean.dto.response.GitReference;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface GitDataService {

    //Async
    //Reference
    @GET("/repos/{owner}/{repo}/git/refs")
    void repoReferences(@Path("owner") String owner, @Path("repo") String name, Callback<List<GitReference>> callback);

    @GET("/repos/{owner}/{repo}/git/refs")
    void repoReferences(@Path("owner") String owner, @Path("repo") String name, @Query("page") int page, Callback<List<GitReference>> callback);

    @GET("/repos/{owner}/{repo}/git/{ref}")
    void repoReference(@Path("owner") String owner, @Path("repo") String name, @Path("ref") String ref, Callback<GitReference> callback);

    //Sync
    @GET("/repos/{owner}/{repo}/git/refs")
    List<GitReference> repoReferences(@Path("owner") String owner, @Path("repo") String name);

    @GET("/repos/{owner}/{repo}/git/refs")
    List<GitReference> repoReferences(@Path("owner") String owner, @Path("repo") String name, @Query("page") int page);

    @GET("/repos/{owner}/{repo}/git/{ref}")
    GitReference repoReference(@Path("owner") String owner, @Path("repo") String name, @Path("ref") String ref);
}
