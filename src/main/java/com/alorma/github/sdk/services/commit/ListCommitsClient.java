package com.alorma.github.sdk.services.commit;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.ListCommit;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 07/09/2014.
 */
public class ListCommitsClient extends GithubClient<ListCommit> {
	private RepoInfo info;
	private int page;

	public ListCommitsClient(Context context, RepoInfo info, int page) {
		super(context);
		this.info = info;
		this.page = page;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		CommitsService commitsService = restAdapter.create(CommitsService.class);
		if (info.branch == null) {
			if (page == 0) {
				commitsService.commits(info.owner, info.name, this);
			} else {
				commitsService.commits(info.owner, info.name, page, this);
			}
		} else {
			if (page == 0) {
				commitsService.commits(info.owner, info.name, info.branch, this);
			} else {
				commitsService.commits(info.owner, info.name, page, info.branch, this);
			}
		}
	}
}
