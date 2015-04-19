package com.alorma.github.sdk.services.gtignore;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.GitIgnoreTemplates;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 28/09/2014.
 */
public class GitIgnoreClient extends GithubClient<GitIgnoreTemplates> {
	public GitIgnoreClient(Context context) {
		super(context);
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		restAdapter.create(GitIgnoreService.class).list(this);
	}
}
