package com.alorma.github.sdk.services.search;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.bean.dto.response.search.UsersSearch;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 08/08/2014.
 */
public class UsersSearchClient extends GithubSearchClient<List<User>> {

	public UsersSearchClient(Context context, String query) {
		super(context, query);
	}

	public UsersSearchClient(Context context, String query, int page) {
		super(context, query, page);
	}

	@Override
	protected void executeFirst(SearchClient searchClient, String query) {
		searchClient.users(query, new Callbacks(this));
	}

	@Override
	protected void executePaginated(SearchClient searchClient, String query, int page) {
		searchClient.usersPaginated(query, page, new Callbacks(this));
	}

	@Override
	protected List<User> executeFirstSync(SearchClient searchClient, String query) {
		return searchClient.users(query).items;
	}

	@Override
	protected List<User> executePaginatedSync(SearchClient searchClient, String query, int page) {
		return searchClient.usersPaginated(query, page).items;
	}

	public class Callbacks implements Callback<UsersSearch> {

		private Callback<List<User>> callback;

		public Callbacks(Callback<List<User>> callback) {
			this.callback = callback;
		}

		@Override
		public void success(UsersSearch usersSearch, Response response) {
			callback.success(usersSearch.items, response);
		}

		@Override
		public void failure(RetrofitError error) {
			callback.failure(error);
		}
	}


}
