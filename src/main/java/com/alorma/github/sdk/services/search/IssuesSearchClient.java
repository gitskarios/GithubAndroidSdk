package com.alorma.github.sdk.services.search;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.dto.response.search.IssuesSearch;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 08/08/2014.
 */
public class IssuesSearchClient extends GithubSearchClient<List<Issue>> {

    public IssuesSearchClient(Context context, String query) {
        super(context, query);
    }

    public IssuesSearchClient(Context context, String query, int page) {
        super(context, query, page);
    }

    @Override
    protected void executeFirst(SearchClient searchClient, String query) {
        searchClient.issues(query, new SearchCallback());
    }

    @Override
    protected void executePaginated(SearchClient searchClient, String query, int page) {
        searchClient.issuesPaginated(query, page, new SearchCallback());
    }

    @Override
    protected List<Issue> executeFirstSync(SearchClient searchClient, String query) {
        return searchClient.issues(query).items;
    }

    @Override
    protected List<Issue> executePaginatedSync(SearchClient searchClient, String query, int page) {
        return searchClient.issuesPaginated(query, page).items;
    }

    private class SearchCallback implements Callback<IssuesSearch> {
        @Override
        public void success(IssuesSearch issuesSearch, Response response) {
            if (getOnResultCallback() != null) {
                getOnResultCallback().onResponseOk(new ArrayList<Issue>(issuesSearch.items), response);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            if (getOnResultCallback() != null) {
                getOnResultCallback().onFail(error);
            }
        }
    }
}
