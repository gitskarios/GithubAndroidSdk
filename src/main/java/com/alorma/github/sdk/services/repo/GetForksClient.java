package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Repo;
import com.alorma.github.sdk.bean.info.RepoInfo;

import java.util.List;

/**
 * Created by a557114 on 05/09/2015.
 */
public class GetForksClient extends GithubRepoClient<List<Repo>> {

    private final int page;

    public GetForksClient(Context context, RepoInfo repoInfo) {
        this(context, repoInfo, 0);
    }
    public GetForksClient(Context context, RepoInfo repoInfo, int page) {
        super(context, repoInfo);
        this.page = page;
    }

    @Override
    protected void executeService(RepoService repoService) {
        if (page == 0) {
            repoService.listForks(getOwner(), getRepo(), this);
        } else {
            repoService.listForks(getOwner(), getRepo(), 0, this);
        }
    }

    @Override
    protected List<Repo> executeServiceSync(RepoService repoService) {
        if (page == 0) {
            return repoService.listForks(getOwner(), getRepo());
        } else {
            return repoService.listForks(getOwner(), getRepo(), 0);
        }
    }
}
