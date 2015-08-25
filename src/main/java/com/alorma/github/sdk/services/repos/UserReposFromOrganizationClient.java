package com.alorma.github.sdk.services.repos;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;

import java.util.List;

public class UserReposFromOrganizationClient extends GithubReposClient {

    public UserReposFromOrganizationClient(Context context) {
        super(context, null);
    }

    public UserReposFromOrganizationClient(Context context, int page) {
        super(context, null, page);
    }

    @Override
    protected void executeUserFirstPage(String sort, ReposService usersService) {
        usersService.userReposListFromOrgs(sort, this);
    }

    @Override
    protected void executeFirstPageByUsername(String username, String sort, ReposService usersService) {

    }

    @Override
    protected void executeUserPaginated(int page, String sort, ReposService usersService) {
        usersService.userReposListFromOrgs(page, sort, this);
    }

    @Override
    protected void executePaginatedByUsername(String username, int page, String sort, ReposService usersService) {

    }

    @Override
    protected List<Repo> executeUserFirstPageSync(String sort, ReposService usersService) {
        return usersService.userReposListFromOrgs(sort);
    }

    @Override
    protected List<Repo> executeFirstPageByUsernameSync(String username, String sort, ReposService usersService) {
        return null;
    }

    @Override
    protected List<Repo> executeUserPaginatedSync(int page, String sort, ReposService usersService) {
        return usersService.userReposListFromOrgs(page, sort);
    }

    @Override
    protected List<Repo> executePaginatedByUsernameSync(String username, int page, String sort, ReposService usersService) {
        return null;
    }

    @Override
    public String getAcceptHeader() {
        return "application/vnd.github.moondragon+json";
    }
}
