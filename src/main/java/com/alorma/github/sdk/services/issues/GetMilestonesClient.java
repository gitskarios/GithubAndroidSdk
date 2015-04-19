package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 14/04/2015.
 */
public class GetMilestonesClient extends GithubClient<List<Milestone>> {

    private IssueInfo issueInfo;

    public GetMilestonesClient(Context context, IssueInfo issueInfo) {
        super(context);
        this.issueInfo = issueInfo;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        IssuesService issueStoryService = restAdapter.create(IssuesService.class);
        new IssueMilestonesCallback(issueInfo, issueStoryService).execute();
    }

    private class IssueMilestonesCallback extends BaseInfiniteCallback<List<Milestone>> {

        private List<Milestone> milestones;
        private IssueInfo issueInfo;
        private IssuesService service;

        public IssueMilestonesCallback(IssueInfo issueInfo, IssuesService service) {
            this.issueInfo = issueInfo;
            this.service = service;
            milestones = new ArrayList<>();
        }

        @Override
        public void execute() {
            service.milestones(issueInfo.repo.owner, issueInfo.repo.name, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            service.milestones(issueInfo.repo.owner, issueInfo.repo.name, nextPage, this);
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
