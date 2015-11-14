package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.dto.response.Contributor;
import com.alorma.github.sdk.bean.info.RepoInfo;

import com.alorma.github.sdk.services.client.GithubListClient;
import java.util.List;
import retrofit.RestAdapter;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetRepoContributorsClient extends GithubListClient<List<Contributor>> {

	private final RepoInfo repoInfo;
	private int page;

	public GetRepoContributorsClient(Context context, RepoInfo repoInfo) {
		this(context, repoInfo, 0);
	}

	public GetRepoContributorsClient(Context context, RepoInfo repoInfo, int page) {
		super(context);
		this.repoInfo = repoInfo;
		this.page = page;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		RepoService repoService = restAdapter.create(RepoService.class);
		if (page == 0) {
			repoService.contributors(getOwner(), getRepo(), this);
		} else {
			repoService.contributors(getOwner(), getRepo(), page, this);
		}
    }

	@Override
	protected List<Contributor> executeServiceSync(RestAdapter restAdapter) {
		RepoService repoService = restAdapter.create(RepoService.class);
		if (page == 0) {
			return repoService.contributors(getOwner(), getRepo());
		} else {
			return repoService.contributors(getOwner(), getRepo(), page);
		}
	}

	private String getOwner() {
		return repoInfo.owner;
	}

	private String getRepo() {
		return repoInfo.name;
	}
}
