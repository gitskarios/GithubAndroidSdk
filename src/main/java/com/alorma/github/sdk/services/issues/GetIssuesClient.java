package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.info.IssueInfo;

import java.util.List;

/**
 * Created by Bernat on 22/08/2014.
 */
public class GetIssuesClient extends GithubIssuesClient<List<Issue>> {

	public GetIssuesClient(Context context, IssueInfo issueInfo) {
		super(context, issueInfo);
	}

	public GetIssuesClient(Context context, IssueInfo issueInfo, int page) {
		super(context, issueInfo, page);
	}

	@Override
	protected void executeFirstPage(IssueInfo issueInfo, IssuesService issuesService) {
		issuesService.issues(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, String.valueOf(issueInfo.state), this);
	}

	@Override
	protected void executePaginated(IssueInfo issueInfo, int page, IssuesService issuesService) {
		issuesService.issues(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, String.valueOf(issueInfo.state), page, this);
	}

	@Override
	protected List<Issue> executeFirstPageSync(IssueInfo issueInfo, IssuesService issuesService) {
		return issuesService.issues(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, String.valueOf(issueInfo.state));
	}

	@Override
	protected List<Issue> executePaginatedSync(IssueInfo issueInfo, int page, IssuesService issuesService) {
		return issuesService.issues(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, String.valueOf(issueInfo.state), page);
	}
}
