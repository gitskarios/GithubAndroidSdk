package com.alorma.github.sdk.services.repo;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.ListUsers;
import com.alorma.github.sdk.bean.info.RepoInfo;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetRepoCollaboratorsClient extends GithubRepoClient<ListUsers> {

    private int page = 0;

    public GetRepoCollaboratorsClient(Context context, RepoInfo repoInfo) {
        this(context, repoInfo, 0);
    }

    public GetRepoCollaboratorsClient(Context context, RepoInfo repoInfo, int page) {
        super(context, repoInfo);
        this.page = page;
    }

    @Override
    protected void executeService(RepoService repoService) {
        if (page == 0) {
            repoService.collaborators(getOwner(), getRepo(), this);
        } else {
            repoService.collaborators(getOwner(), getRepo(), page, this);
        }
    }
}
