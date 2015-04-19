package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.gitskarios.basesdk.client.BaseClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 22/08/2014.
 */
public abstract class BaseIssuesClient<K> extends GithubClient<K> {

	private int page;
	private IssueInfo issueInfo;

	public BaseIssuesClient(Context context, IssueInfo issueInfo, int page) {
		this(context, issueInfo);
		this.page = page;
	}

	public BaseIssuesClient(Context context, IssueInfo issueInfo) {
		super(context);
		this.issueInfo = issueInfo;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		IssuesService issuesService = restAdapter.create(IssuesService.class);
		if (page == 0) {
			executeFirstPage(issueInfo, issuesService);
		} else {
			executePaginated(issueInfo, page, issuesService);
		}
	}

	protected abstract void executeFirstPage(IssueInfo issueInfo, IssuesService issuesService);

	protected abstract void executePaginated(IssueInfo issueInfo, int page, IssuesService issuesService);

	@Override
	public String getAcceptHeader() {
		return "application/vnd.github.v3.text+json";
	}
}
