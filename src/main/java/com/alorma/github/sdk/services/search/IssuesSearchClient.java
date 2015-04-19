package com.alorma.github.sdk.services.search;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.ListIssues;
import com.alorma.github.sdk.bean.dto.response.search.IssuesSearch;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 08/08/2014.
 */
public class IssuesSearchClient extends GithubSearchClient<ListIssues> {

    public IssuesSearchClient(Context context, String query) {
        super(context, query);
    }

    public IssuesSearchClient(Context context, String query, int page) {
        super(context, query, page);
    }

    @Override
    protected void executeFirst(SearchClient searchClient, String query) {
        searchClient.issues(query, new SearcCallback());
    }

    @Override
    protected void executePaginated(SearchClient searchClient, String query, int page) {
        searchClient.issuesPaginated(query, page, new SearcCallback());
    }

    private class SearcCallback implements Callback<IssuesSearch> {
        @Override
        public void success(IssuesSearch issuesSearch, Response response) {
            if (getOnResultCallback() != null) {
                getOnResultCallback().onResponseOk(new ListIssues(issuesSearch.items), response);
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
