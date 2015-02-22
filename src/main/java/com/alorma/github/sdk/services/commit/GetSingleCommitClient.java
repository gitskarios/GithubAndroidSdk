package com.alorma.github.sdk.services.commit;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.BaseClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 22/12/2014.
 */
public class GetSingleCommitClient extends BaseClient<Commit> {
	private RepoInfo info;
	private String sha;

	public GetSingleCommitClient(Context context, RepoInfo info, String sha) {
		super(context);
		this.info = info;
		this.sha = sha;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		restAdapter.create(CommitsService.class).singleCommit(info.owner, info.name, sha, this);
	}
}
