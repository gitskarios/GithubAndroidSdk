package com.alorma.github.sdk.services.user;

import com.alorma.github.sdk.bean.dto.response.ListEmails;
import com.alorma.github.sdk.bean.dto.response.ListUsers;
import com.alorma.github.sdk.bean.dto.response.User;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Bernat on 12/07/2014.
 */
public interface UsersService {

	@GET("/users/{user}")
	void getSingleUser(@Path("user") String user, Callback<User> callback);

	@GET("/user/emails")
	void userEmails(Callback<ListEmails> callback);

	// Followers
	@GET("/user/followers")
	void followers(Callback<ListUsers> callback);

	@GET("/users/{username}/followers")
	void followers(@Path("username") String username, Callback<ListUsers> callback);

	@GET("/user/followers")
	void followers(@Query("page") int page, Callback<ListUsers> callback);

	@GET("/users/{username}/followers")
	void followers(@Path("username") String username, @Query("page") int page, Callback<ListUsers> callback);

	// Following
	@GET("/user/following")
	void following(Callback<ListUsers> callback);

	@GET("/users/{username}/following")
	void following(@Path("username") String username, Callback<ListUsers> callback);

	@GET("/user/following")
	void following(@Query("page") int page, Callback<ListUsers> callback);

	@GET("/users/{username}/following")
	void following(@Path("username") String username, @Query("page") int page, Callback<ListUsers> callback);

	@GET("/user")
	void me(Callback<User> userCallback);

	// FOLLOWING USER

	@GET("/user/following/{username}")
	void checkFollowing(@Path("username") String username, Callback<Object> callback);

	@PUT("/user/following/{username}")
	void followUser(@Path("username") String username, Callback<Object> callback);

	@DELETE("/user/following/{username}")
	void unfollowUser(@Path("username") String username, Callback<Object> callback);
}
