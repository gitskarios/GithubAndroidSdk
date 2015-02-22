package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.BaseClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 13/07/2014.
 */
public abstract class BaseRepoClient<K> extends BaseClient<K> {

	private RepoInfo repoInfo;

	public BaseRepoClient(Context context, RepoInfo repoInfo) {
		super(context);
		this.repoInfo = repoInfo;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		RepoService repoService = restAdapter.create(RepoService.class);
		executeService(repoService);
	}

	protected abstract void executeService(RepoService repoService);

	public String getOwner() {
		return repoInfo.owner;
	}

	public String getRepo() {
		return repoInfo.name;
	}

	public String getBranch() {
		return repoInfo.branch;
	}
}
