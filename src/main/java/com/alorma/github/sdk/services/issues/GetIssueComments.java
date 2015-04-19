package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.ListIssueComments;
import com.alorma.github.sdk.bean.info.IssueInfo;

/**
 * Created by Bernat on 23/08/2014.
 */
public class GetIssueComments extends GithubIssuesClient<ListIssueComments> {

	public GetIssueComments(Context context, IssueInfo issueInfo, int page) {
		super(context, issueInfo, page);
	}

	public GetIssueComments(Context context, IssueInfo issueInfo) {
		super(context, issueInfo);
	}

	@Override
	protected void executeFirstPage(IssueInfo issueInfo, IssuesService issuesService) {
		issuesService.comments(issueInfo.repo.owner, issueInfo.repo.name, issueInfo.num, this);
	}

	@Override
	protected void executePaginated(IssueInfo issueInfo, int page, IssuesService issuesService) {
		issuesService.comments(issueInfo.repo.owner, issueInfo.repo.name, issueInfo.num, page, this);
	}

	@Override
	public String getAcceptHeader() {
		return "application/vnd.github.v3.html+json";
	}
}
