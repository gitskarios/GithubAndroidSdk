package com.alorma.github.sdk.services.orgs;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.services.repos.GithubReposClient;
import com.alorma.github.sdk.services.repos.ReposService;

import java.util.List;

import retrofit.RestAdapter;

public class OrgsReposClient extends GithubReposClient {

	public OrgsReposClient(Context context, String org) {
		super(context, org);
	}

	public OrgsReposClient(Context context, String org, int page) {
		super(context, org, page);
	}

	@Override
	protected void executeUserFirstPage(String sort, ReposService orgsService) {

	}

	@Override
	protected void executeFirstPageByUsername(String org, String sort, ReposService orgsService) {
		orgsService.orgsReposList(org, sort, this);
	}

	@Override
	protected void executeUserPaginated(int page, String sort, ReposService orgsService) {

	}

	@Override
	protected void executePaginatedByUsername(String org, int page, String sort, ReposService orgsService) {
		orgsService.orgsReposList(org, page, sort, this);
	}

	@Override
	protected List<Repo> executeUserFirstPageSync(String sort, ReposService orgsService) {
		return null;
	}

	@Override
	protected List<Repo> executeFirstPageByUsernameSync(String org, String sort, ReposService orgsService) {
		return orgsService.orgsReposList(org, sort);
	}

	@Override
	protected List<Repo> executeUserPaginatedSync(int page, String sort, ReposService orgsService) {
		return null;
	}

	@Override
	protected List<Repo> executePaginatedByUsernameSync(String org, int page, String sort, ReposService orgsService) {
		return orgsService.orgsReposList(org, page, sort);
	}
}
