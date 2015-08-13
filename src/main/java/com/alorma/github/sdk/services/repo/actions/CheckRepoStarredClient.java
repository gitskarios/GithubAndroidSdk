package com.alorma.github.sdk.services.repo.actions;

import android.content.Context;

import com.alorma.github.basesdk.client.BaseClient;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.user.follow.OnCheckFollowingUser;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 07/08/2014.
 */
public class CheckRepoStarredClient extends GithubClient<Object> implements BaseClient.OnResultCallback<Object> {
    private String repo;
    private String owner;
    private OnCheckStarredRepo onCheckStarredRepo;

    public CheckRepoStarredClient(Context context, String owner, String repo) {
        super(context);
        this.owner = owner;
        this.repo = repo;
        setOnResultCallback(this);
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        ActionsService actionsService = restAdapter.create(ActionsService.class);
        actionsService.checkIfRepoIsStarred(owner, repo, this);
    }

    @Override
    public void onResponseOk(Object o, Response r) {
        if (r != null && r.getStatus() == 204) {
            if (onCheckStarredRepo != null) {
                onCheckStarredRepo.onCheckStarredRepo(repo, true);
            }
        }
    }

    @Override
    public void onFail(RetrofitError error) {
        if (error != null && error.getResponse() != null && error.getResponse().getStatus() == 404) {
            if (onCheckStarredRepo != null) {
                onCheckStarredRepo.onCheckStarredRepo(repo, false);
            }
        }
    }

    public void setOnCheckStarredRepo(OnCheckStarredRepo onCheckStarredRepo) {
        this.onCheckStarredRepo = onCheckStarredRepo;
    }
}
