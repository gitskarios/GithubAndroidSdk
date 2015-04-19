package com.alorma.github.sdk.services.pullrequest;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.ListIssues;

public class GetPullRequestsClient extends GithubPullRequestsClient<ListIssues> {

	public GetPullRequestsClient(Context context, String owner, String repository) {
		super(context, owner, repository);
	}

	public GetPullRequestsClient(Context context, String owner, String repository, int page) {
		super(context, owner, repository);
	}

	@Override
	protected void executeFirstPage(String owner, String repository, PullRequestsService issuesService) {
		issuesService.pulls(owner, repository, this);
	}

	@Override
	protected void executePaginated(String owner, String repository, int page, PullRequestsService issuesService) {
		issuesService.pulls(owner, repository, page, this);
	}
}
