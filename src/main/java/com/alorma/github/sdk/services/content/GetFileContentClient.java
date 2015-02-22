package com.alorma.github.sdk.services.content;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.BaseClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 10/09/2014.
 */
public class GetFileContentClient extends BaseClient<Content> {

	private String owner;
	private String repo;
	private String path;
	private String head;

	public GetFileContentClient(Context context, RepoInfo info, String path) {
		super(context);
		this.owner = info.owner;
		this.repo = info.name;
		this.path = path;
		this.head = info.branch;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		ContentService contentService = restAdapter.create(ContentService.class);

		if (head == null) {
			contentService.fileContent(owner, repo, path, this);
		} else {
			contentService.fileContent(owner, repo, path, head, this);
		}
	}
}
