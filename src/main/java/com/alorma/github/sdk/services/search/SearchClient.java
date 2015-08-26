package com.alorma.github.sdk.services.search;

import com.alorma.github.sdk.bean.dto.response.search.IssuesSearch;
import com.alorma.github.sdk.bean.dto.response.search.ReposSearch;
import com.alorma.github.sdk.bean.dto.response.search.UsersSearch;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Bernat on 08/08/2014.
 */
public interface SearchClient {

    //Async
    @GET("/search/repositories")
    void repos(@Query("q") String query, Callback<ReposSearch> callback);

    @GET("/search/repositories")
    void reposPaginated(@Query("q") String query, @Query("page") int page, Callback<ReposSearch> callback);

    @GET("/search/issues")
    void issues(@Query("q") String query, Callback<IssuesSearch> callback);

    @GET("/search/issues")
    void issuesPaginated(@Query("q") String query, @Query("page") int page, Callback<IssuesSearch> callback);

    @GET("/search/users")
    void users(@Query("q") String query, Callback<UsersSearch> callback);
	
	@GET("/search/users")
	void usersPaginated(@Query("q") String query, @Query("page") int page, Callback<UsersSearch> callback);

    //Sync
    @GET("/search/repositories")
    ReposSearch repos(@Query("q") String query);

    @GET("/search/repositories")
    ReposSearch reposPaginated(@Query("q") String query, @Query("page") int page);

    @GET("/search/issues")
    IssuesSearch issues(@Query("q") String query);

    @GET("/search/issues")
    IssuesSearch issuesPaginated(@Query("q") String query, @Query("page") int page);

    @GET("/search/users")
    UsersSearch users(@Query("q") String query);

    @GET("/search/users")
    UsersSearch usersPaginated(@Query("q") String query, @Query("page") int page);

}
