package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.ListContributors;
import com.alorma.github.sdk.bean.info.RepoInfo;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetRepoContributorsClient extends BaseRepoClient<ListContributors> {

	private int page;

	public GetRepoContributorsClient(Context context, RepoInfo repoInfo) {
		this(context, repoInfo, 0);
	}
	public GetRepoContributorsClient(Context context, RepoInfo repoInfo, int page) {
		super(context, repoInfo);
		this.page = page;
	}

	@Override
    protected void executeService(RepoService repoService) {
		if (page == 0) {
			repoService.contributors(getOwner(), getRepo(), this);
		} else {
			repoService.contributors(getOwner(), getRepo(), page, this);
		}
    }
}
