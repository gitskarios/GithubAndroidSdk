package com.alorma.github.sdk.services.commit;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Branch;
import com.alorma.github.sdk.bean.dto.response.ListCommit;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.BaseClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 07/09/2014.
 */
public class ListCommitsClient extends BaseClient<ListCommit> {
	private RepoInfo info;
	private int page;
	private Branch branch;

	public ListCommitsClient(Context context, RepoInfo info, int page, Branch branch) {
		super(context);
		this.info = info;
		this.page = page;
		this.branch = branch;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		CommitsService commitsService = restAdapter.create(CommitsService.class);
		if (branch == null) {
			if (page == 0) {
				commitsService.commits(info.owner, info.repo, this);
			} else {
				commitsService.commits(info.owner, info.repo, page, this);
			}
		} else {
			if (page == 0) {
				commitsService.commits(info.owner, info.repo, branch.name, this);
			} else {
				commitsService.commits(info.owner, info.repo, page, branch.name, this);
			}
		}
	}
}
