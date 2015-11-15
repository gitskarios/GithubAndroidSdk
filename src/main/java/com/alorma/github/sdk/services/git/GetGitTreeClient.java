package com.alorma.github.sdk.services.git;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.GitTree;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;
import retrofit.RestAdapter;
import rx.Observable;

public class GetGitTreeClient extends GithubClient<GitTree> {

    private final RepoInfo info;
    private final boolean recursive;

    public GetGitTreeClient(Context context, RepoInfo repoInfo, boolean recursive) {
        super(context);
        this.info = repoInfo;
        this.recursive = recursive;
    }

    @Override
    protected Observable<GitTree> getApiObservable(RestAdapter restAdapter) {
        if (recursive) {
            return restAdapter.create(GitDataService.class)
                .repoTreeRecursive(info.owner, info.name, info.branch);
        } else {
            return restAdapter.create(GitDataService.class)
                .repoTree(info.owner, info.name, info.branch);
        }
    }
}
