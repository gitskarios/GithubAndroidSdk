package com.alorma.github.sdk.services.pullrequest;

import android.content.Context;

import com.alorma.github.sdk.services.client.BaseClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 22/08/2014.
 */
public abstract class BasePullRequestsClient<K> extends BaseClient<K> {
    private String owner;
    private String repository;
	private int num;
	private int page;

    public BasePullRequestsClient(Context context, String owner, String repository) {
        super(context);
        this.owner = owner;
        this.repository = repository;
		this.num = num;
	}

	public BasePullRequestsClient(Context context, String owner, String repository, int page) {
		this(context, owner, repository);
		this.page = page;
	}

    @Override
    protected void executeService(RestAdapter restAdapter) {
        PullRequestsService issuesService = restAdapter.create(PullRequestsService.class);
        if (page == 0) {
            executeFirstPage(owner, repository, issuesService);
        } else {
            executePaginated(owner, repository, page, issuesService);
        }
    }

    protected abstract void executeFirstPage(String owner, String repository, PullRequestsService issuesService);

    protected abstract void executePaginated(String owner, String repository, int page, PullRequestsService issuesService);

    @Override
    public String getAcceptHeader() {
        return "application/vnd.github.v3.text+json";
    }
}
