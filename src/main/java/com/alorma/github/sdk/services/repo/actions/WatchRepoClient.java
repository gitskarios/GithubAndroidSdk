package com.alorma.github.sdk.services.repo.actions;

import android.content.Context;
import com.alorma.github.sdk.services.client.GithubClient;
import retrofit.RestAdapter;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by Bernat on 07/08/2014.
 */
public class WatchRepoClient extends GithubClient<Response> {

    private final String owner;
    private final String repo;

    public WatchRepoClient(Context context, String owner, String repo) {
        super(context);
        this.owner = owner;
        this.repo = repo;
    }

    @Override
    protected Observable<Response> getApiObservable(RestAdapter restAdapter) {
        return restAdapter.create(RepoActionsService.class).watchRepo(owner, repo, "");
    }
}
