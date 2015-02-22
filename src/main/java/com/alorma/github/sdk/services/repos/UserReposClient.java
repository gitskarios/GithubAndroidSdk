package com.alorma.github.sdk.services.repos;

import android.content.Context;

public class UserReposClient extends BaseReposClient {

	public UserReposClient(Context context) {
		super(context);
	}

	public UserReposClient(Context context, String username) {
		super(context, username);
	}

	public UserReposClient(Context context, String username, int page) {
		super(context, username, page);
	}

	@Override
	protected void executeUserFirstPage(String sort, ReposService usersService) {
		usersService.userReposList(sort, this);
	}

	@Override
	protected void executeFirstPageByUsername(String username, String sort, ReposService usersService) {
		usersService.userReposList(username, sort, this);
	}

	@Override
	protected void executeUserPaginated(int page, String sort, ReposService usersService) {
		usersService.userReposList(page, sort, this);
	}

	@Override
	protected void executePaginatedByUsername(String username, int page, String sort, ReposService usersService) {
		usersService.userReposList(username, page, sort, this);
	}

	@Override
	public String getAcceptHeader() {
		return "application/vnd.github.moondragon+json";
	}
}
