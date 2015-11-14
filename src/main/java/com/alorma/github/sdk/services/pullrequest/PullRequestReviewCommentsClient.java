package com.alorma.github.sdk.services.pullrequest;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.ReviewComment;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import com.alorma.github.sdk.services.client.GithubListClient;
import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by a557114 on 25/07/2015.
 */
public class PullRequestReviewCommentsClient extends GithubListClient<List<ReviewComment>> {

    private IssueInfo info;
    private final int page;

    public PullRequestReviewCommentsClient(Context context, IssueInfo info) {
        this(context, info, 0);
    }

    public PullRequestReviewCommentsClient(Context context, IssueInfo info, int page) {
        super(context);
        this.info = info;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        PullRequestsService service = restAdapter.create(PullRequestsService.class);

        if (page == 0) {
            service.reviewComments(info.repoInfo.owner, info.repoInfo.name, info.num, this);
        } else {
            service.reviewComments(info.repoInfo.owner, info.repoInfo.name, info.num, page, this);
        }
    }

    @Override
    protected List<ReviewComment> executeServiceSync(RestAdapter restAdapter) {
        PullRequestsService service = restAdapter.create(PullRequestsService.class);

        if (page == 0) {
            return service.reviewComments(info.repoInfo.owner, info.repoInfo.name, info.num);
        } else {
            return service.reviewComments(info.repoInfo.owner, info.repoInfo.name, info.num, page);
        }

    }

    @Override
    public String getAcceptHeader() {
        return "application/vnd.github.v3.full+json";
    }
}
