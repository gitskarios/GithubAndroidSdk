package com.alorma.github.sdk.services.search;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.ListRepos;
import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.dto.response.search.ReposSearch;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 08/08/2014.
 */
public class RepoSearchClient extends BaseSearchClient<ListRepos> {

	public RepoSearchClient(Context context, String query) {
		super(context, query);
	}

	public RepoSearchClient(Context context, String query, int page) {
		super(context, query, page);
	}

	@Override
	protected void executeFirst(SearchClient searchClient, String query) {
		searchClient.repos(query, new Callbacks(this));
	}

	@Override
	protected void executePaginated(SearchClient searchClient, String query, int page) {
		searchClient.reposPaginated(query, page, new Callbacks(this));
	}

	public class Callbacks implements Callback<ReposSearch> {

		private Callback<ListRepos> callback;

		public Callbacks(Callback<ListRepos> callback) {
			this.callback = callback;
		}

		@Override
		public void success(ReposSearch reposSearch, Response response) {
			callback.success(reposSearch.items, response);
		}

		@Override
		public void failure(RetrofitError error) {
			callback.failure(error);
		}
	}

}
