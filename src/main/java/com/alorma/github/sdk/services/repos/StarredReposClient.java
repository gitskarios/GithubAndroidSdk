package com.alorma.github.sdk.services.repos;

import android.content.Context;

/**
 * Created by Bernat on 17/07/2014.
 */
public class StarredReposClient extends BaseReposClient{

    public StarredReposClient(Context context) {
        super(context);
    }

    public StarredReposClient(Context context, String username) {
        super(context, username);
    }

    public StarredReposClient(Context context, String username, int page) {
        super(context, username, page);
    }

    @Override
    protected void executeUserFirstPage(String sort, ReposService usersService) {
        usersService.userStarredReposList(this);
    }

    @Override
    protected void executeFirstPageByUsername(String username, String sort, ReposService usersService) {
        usersService.userStarredReposList(username, this);
    }

    @Override
    protected void executeUserPaginated(int page, String sort, ReposService usersService) {
        usersService.userStarredReposList(page, this);
    }

    @Override
    protected void executePaginatedByUsername(String username, int page, String sort, ReposService usersService) {
        usersService.userStarredReposList(username, page, this);
    }


}
