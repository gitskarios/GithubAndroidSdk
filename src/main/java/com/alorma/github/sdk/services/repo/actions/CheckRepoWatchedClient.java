package com.alorma.github.sdk.services.repo.actions;

import android.content.Context;
import com.alorma.github.sdk.services.client.GithubClient;
import retrofit.RestAdapter;
import retrofit.client.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Bernat on 07/08/2014.
 */
public class CheckRepoWatchedClient extends GithubClient<Boolean> {
    private String repo;
    private String owner;

    public CheckRepoWatchedClient(Context context, String owner, String repo) {
        super(context);
        this.owner = owner;
        this.repo = repo;
    }

    @Override
    protected Observable<Boolean> getApiObservable(RestAdapter restAdapter) {
        return restAdapter.create(RepoActionsService.class)
            .checkIfRepoIsWatched(owner, repo)
            .map(new Func1<Response, Boolean>() {
                @Override
                public Boolean call(Response r) {
                    return r != null && r.getStatus() == 204;
                }
            });
    }
}
