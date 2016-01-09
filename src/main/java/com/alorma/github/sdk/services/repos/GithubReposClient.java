package com.alorma.github.sdk.services.repos;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.services.client.GithubListClient;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 13/07/2014.
 */
public abstract class GithubReposClient extends GithubListClient<List<Repo>> {
    private String username;
    private int page;
    private String sort;

    public GithubReposClient() {
        this(null, null);
    }

    public GithubReposClient(String username, String sort) {
        this(username, sort, 0);
    }

    public GithubReposClient(String username, String sort, int page) {
        super();
        this.username = username;
        this.sort = sort;
        this.page = page;
    }

    @Override
    protected ApiSubscriber getApiObservable(RestAdapter restAdapter) {
        return new ApiSubscriber() {
            @Override
            protected void call(RestAdapter restAdapter) {
                ReposService reposService = restAdapter.create(ReposService.class);
                if (page == 0) {
                    if (username == null) {
                        executeUserFirstPage(sort, reposService, this);
                    } else {
                        executeFirstPageByUsername(username, sort, reposService, this);
                    }
                } else {
                    if (username == null) {
                        executeUserPaginated(page, sort, reposService, this);
                    } else {
                        executePaginatedByUsername(username, page, sort, reposService, this);
                    }
                }
            }
        };
    }

    protected abstract void executeUserFirstPage(String sort, ReposService usersService, ApiSubscriber apiSubscriber);

    protected abstract void executeFirstPageByUsername(String username, String sort, ReposService usersService, ApiSubscriber apiSubscriber);

    protected abstract void executeUserPaginated(int page, String sort, ReposService usersService, ApiSubscriber apiSubscriber);

    protected abstract void executePaginatedByUsername(String username, int page, String sort, ReposService usersService,
                                                       ApiSubscriber apiSubscriber);
}
