package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.IssueComment;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 06/09/2014.
 */
public class NewIssueCommentClient extends GithubClient<IssueComment> {
	private String body;
	private String owner;
	private String repo;
	private int num;

	public NewIssueCommentClient(Context context, IssueInfo issueInfo, String body) {
		super(context);
		this.body = body;
		owner = issueInfo.repoInfo.owner;
		repo = issueInfo.repoInfo.name;
		num = issueInfo.num;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		IssueComment comment = new IssueComment();
		comment.body = body;

		IssuesService service = restAdapter.create(IssuesService.class);
		service.addComment(owner, repo, num, comment, this);
	}
}
