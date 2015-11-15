package com.alorma.github.sdk.services.pullrequest;

import android.content.Context;

import android.util.Pair;
import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.CommitFile;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import com.alorma.github.sdk.services.client.GithubListClient;
import java.util.List;

import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 17/06/2015.
 */
public class GetPullRequestFiles extends GithubListClient<List<CommitFile>> {

    private IssueInfo info;
    private int page;

    public GetPullRequestFiles(Context context, IssueInfo info) {
        super(context);
        this.info = info;
    }

    public GetPullRequestFiles(Context context, IssueInfo info, int page) {
        super(context);
        this.info = info;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        PullRequestsService pullRequestsService = restAdapter.create(PullRequestsService.class);
        if (page == 0) {
            pullRequestsService.files(info.repoInfo.owner, info.repoInfo.name, info.num, this);
        } else {
            pullRequestsService.files(info.repoInfo.owner, info.repoInfo.name, info.num, page, this);
        }
    }

    @Override
    protected List<CommitFile> executeServiceSync(RestAdapter restAdapter) {
        PullRequestsService pullRequestsService = restAdapter.create(PullRequestsService.class);
        if (page == 0) {
            return pullRequestsService.files(info.repoInfo.owner, info.repoInfo.name, info.num);
        } else {
            return pullRequestsService.files(info.repoInfo.owner, info.repoInfo.name, info.num, page);
        }
    }

}
