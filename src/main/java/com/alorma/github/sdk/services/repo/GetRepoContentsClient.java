package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.bean.info.RepoInfo;

import com.alorma.github.sdk.services.client.GithubListClient;
import java.util.List;
import retrofit.RestAdapter;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetRepoContentsClient extends GithubListClient<List<Content>> {

	private RepoInfo repoInfo;
	private String path = null;

	public GetRepoContentsClient(Context context, RepoInfo repoInfo) {
		this(context, repoInfo, null);
	}

	public GetRepoContentsClient(Context context, RepoInfo repoInfo, String path) {
		super(context);
		this.repoInfo = repoInfo;
		this.path = path;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		RepoService repoService = restAdapter.create(RepoService.class);
		if (path == null) {
			if (getBranch() == null) {
				repoService.contents(getOwner(), getRepo(), this);
			} else {
				repoService.contentsByRef(getOwner(), getRepo(), getBranch(), this);
			}
		} else {
			if (getBranch() == null) {
				repoService.contents(getOwner(), getRepo(), path, this);
			} else {
				repoService.contentsByRef(getOwner(), getRepo(), path, getBranch(), this);
			}
		}
	}

	@Override
	protected List<Content> executeServiceSync(RestAdapter restAdapter) {
		RepoService repoService = restAdapter.create(RepoService.class);
		if (path == null) {
			if (getBranch() == null) {
				return repoService.contents(getOwner(), getRepo());
			} else {
				return repoService.contentsByRef(getOwner(), getRepo(), getBranch());
			}
		} else {
			if (getBranch() == null) {
				return repoService.contents(getOwner(), getRepo(), path);
			} else {
				return repoService.contentsByRef(getOwner(), getRepo(), path, getBranch());
			}
		}
	}

	private String getOwner() {
		return repoInfo.owner;
	}

	private String getRepo() {
		return repoInfo.name;
	}

	private String getBranch() {
		return repoInfo.branch;
	}
}
