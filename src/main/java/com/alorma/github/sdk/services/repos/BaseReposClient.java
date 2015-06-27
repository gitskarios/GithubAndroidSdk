package com.alorma.github.sdk.services.repos;

import android.content.Context;

import com.alorma.github.sdk.R;
import com.alorma.github.sdk.bean.dto.response.ListRepos;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.utils.GitskariosSettings;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 13/07/2014.
 */
public abstract class BaseReposClient extends GithubClient<ListRepos> {
    private String username;
    private int page;

    public BaseReposClient(Context context) {
        this(context, null);
    }

    public BaseReposClient(Context context, String username) {
        this(context, username, 0);
    }
   
    public BaseReposClient(Context context, String username, int page) {
        super(context);
        this.username = username;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        ReposService reposService = restAdapter.create(ReposService.class);
		GitskariosSettings settings = new GitskariosSettings(context);
		String sort = settings.getRepoSort(context.getString(R.string.sort_pushed_value));
        if (page == 0) {
            if (username == null) {
                executeUserFirstPage(sort, reposService);
            } else {
                executeFirstPageByUsername(username, sort, reposService);
            }
        } else {
            if (username == null) {
                executeUserPaginated(page, sort, reposService);
            } else {
                executePaginatedByUsername(username, page, sort, reposService);
            }
        }
    }

    protected abstract void executeUserFirstPage(String sort, ReposService usersService);
    protected abstract void executeFirstPageByUsername(String username, String sort, ReposService usersService);
    protected abstract void executeUserPaginated(int page, String sort, ReposService usersService);
    protected abstract void executePaginatedByUsername(String username, int page, String sort, ReposService usersService);
}
