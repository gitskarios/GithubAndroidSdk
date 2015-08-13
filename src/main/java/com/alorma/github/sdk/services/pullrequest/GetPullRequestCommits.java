package com.alorma.github.sdk.services.pullrequest;

import android.content.Context;

import com.alorma.github.basesdk.client.BaseClient;
import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by Bernat on 17/06/2015.
 */
public class GetPullRequestCommits extends GithubClient<List<Commit>> {

    private IssueInfo info;
    private int page;

    public GetPullRequestCommits(Context context, IssueInfo info) {
        super(context);
        this.info = info;
    }

    public GetPullRequestCommits(Context context, IssueInfo info, int page) {
        super(context);
        this.info = info;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        PullRequestsService pullRequestsService = restAdapter.create(PullRequestsService.class);
        pullRequestsService.commits(info.repoInfo.owner, info.repoInfo.name, info.num, new CommitsCallback(context, info, pullRequestsService, getOnResultCallback()));
    }

    private class CommitsCallback extends BaseInfiniteCallback<List<Commit>> {
        private final Context context;
        private IssueInfo info;
        private PullRequestsService pullRequestsService;
        private final OnResultCallback<List<Commit>> onResultCallback;
        private List<Commit> commits;

        public CommitsCallback(Context context, IssueInfo info, PullRequestsService pullRequestsService, OnResultCallback<List<Commit>> onResultCallback) {
            this.context = context;
            this.info = info;
            this.pullRequestsService = pullRequestsService;
            this.onResultCallback = onResultCallback;
            this.commits = new ArrayList<>();
        }

        @Override
        protected void executePaginated(int nextPage) {
            pullRequestsService.commits(info.repoInfo.owner, info.repoInfo.name, info.num, nextPage, this);
        }

        @Override
        protected void executeNext() {
            if (onResultCallback != null) {
                onResultCallback.onResponseOk(this.commits, null);
            }
        }

        @Override
        protected void response(List<Commit> commits) {
            this.commits.addAll(commits);
        }

        @Override
        public void execute() {
            pullRequestsService.commits(info.repoInfo.owner, info.repoInfo.name, info.num, this);
        } else {
            pullRequestsService.commits(info.repoInfo.owner, info.repoInfo.name, info.num, page, this);
        }
    }

    @Override
    protected List<Commit> executeServiceSync(RestAdapter restAdapter) {
        PullRequestsService pullRequestsService = restAdapter.create(PullRequestsService.class);
        if (page == 0) {
            return pullRequestsService.commits(info.repoInfo.owner, info.repoInfo.name, info.num);
        } else {
            return pullRequestsService.commits(info.repoInfo.owner, info.repoInfo.name, info.num, page);
        }
    }

}
