package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.IssueState;
import com.alorma.github.sdk.bean.dto.response.ListIssues;
import com.alorma.github.sdk.bean.info.IssueInfo;

/**
 * Created by Bernat on 22/08/2014.
 */
public class GetIssuesClient extends BaseIssuesClient<ListIssues> {

	public GetIssuesClient(Context context, IssueInfo issueInfo) {
		super(context, issueInfo);
	}

	public GetIssuesClient(Context context, IssueInfo issueInfo, int page) {
		super(context, issueInfo, page);
	}

	@Override
	protected void executeFirstPage(IssueInfo issueInfo, IssuesService issuesService) {
		issuesService.issues(issueInfo.repo.owner, issueInfo.repo.name, String.valueOf(issueInfo.state), this);
	}

	@Override
	protected void executePaginated(IssueInfo issueInfo, int page, IssuesService issuesService) {
		issuesService.issues(issueInfo.repo.owner, issueInfo.repo.name, String.valueOf(issueInfo.state), page, this);
	}
}
