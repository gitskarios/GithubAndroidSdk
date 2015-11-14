package com.alorma.github.sdk.services.repo;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubListClient;
import java.util.List;
import retrofit.RestAdapter;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetRepoCollaboratorsClient extends GithubListClient<List<User>> {

    private final RepoInfo repoInfo;
    private int page = 0;

    public GetRepoCollaboratorsClient(Context context, RepoInfo repoInfo) {
        this(context, repoInfo, 0);
    }

    public GetRepoCollaboratorsClient(Context context, RepoInfo repoInfo, int page) {
        super(context);
        this.repoInfo = repoInfo;
        this.page = page;
    }
    
    @Override
    protected void executeService(RestAdapter restAdapter) {
        RepoService repoService = restAdapter.create(RepoService.class);
        if (page == 0) {
            repoService.collaborators(repoInfo.owner, repoInfo.name, this);
        } else {
            repoService.collaborators(repoInfo.owner, repoInfo.name, page, this);
        }
    }

    @Override
    protected List<User> executeServiceSync(RestAdapter restAdapter) {
        RepoService repoService = restAdapter.create(RepoService.class);if (page == 0) {
            return repoService.collaborators(repoInfo.owner, repoInfo.name);
        } else {
            return repoService.collaborators(repoInfo.owner, repoInfo.name, page);
        }
    }
}
