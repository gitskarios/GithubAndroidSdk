package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.Converter;

/**
 * Created by Bernat on 14/04/2015.
 */
public class GetMilestonesClient extends GithubClient<List<Milestone>> {

    private RepoInfo repoInfo;

    public GetMilestonesClient(Context context, RepoInfo repoInfo) {
        super(context);
        this.repoInfo = repoInfo;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        IssuesService issueStoryService = restAdapter.create(IssuesService.class);
        new IssueMilestonesCallback(repoInfo, issueStoryService).execute();
    }

    @Override
    protected List<Milestone> executeServiceSync(RestAdapter restAdapter) {
        IssuesService issueStoryService = restAdapter.create(IssuesService.class);
        List<Milestone> milestones = new ArrayList<>();
        boolean hasMore = true;
        while(hasMore)
            hasMore = milestones.addAll(issueStoryService.milestones(repoInfo.owner, repoInfo.name));
        return milestones;
    }

    private class IssueMilestonesCallback extends BaseInfiniteCallback<List<Milestone>> {

        private List<Milestone> milestones;
        private RepoInfo repoInfo;
        private IssuesService service;

        public IssueMilestonesCallback(RepoInfo repoInfo, IssuesService service) {
            this.repoInfo = repoInfo;
            this.service = service;
            milestones = new ArrayList<>();
        }

        @Override
        public void execute() {
            service.milestones(repoInfo.owner, repoInfo.name, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            service.milestones(repoInfo.owner, repoInfo.name, nextPage, this);
        }

        @Override
        protected void executeNext() {
            if (getOnResultCallback() != null) {
                getOnResultCallback().onResponseOk(milestones, null);
            }
        }

        @Override
        protected void response(List<Milestone> milestones) {
            this.milestones.addAll(milestones);
        }
    }

}
