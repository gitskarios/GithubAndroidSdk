package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.ListContributors;
import com.alorma.github.sdk.bean.info.RepoInfo;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetRepoContributorsClient extends BaseRepoClient<ListContributors> {

	public GetRepoContributorsClient(Context context, RepoInfo repoInfo) {
		super(context, repoInfo);
	}

	@Override
    protected void executeService(RepoService repoService) {
        repoService.contributors(getOwner(), getRepo(), this);
    }
}
