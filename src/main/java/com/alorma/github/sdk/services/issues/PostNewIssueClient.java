package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.request.IssueRequest;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.BaseClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 23/08/2014.
 */
public class PostNewIssueClient extends BaseClient<Issue> {

    private IssueRequest issue;
    private RepoInfo repoInfo;

    public PostNewIssueClient(Context context, RepoInfo repoInfo, IssueRequest issue) {
        super(context);
        this.repoInfo = repoInfo;
        this.repoInfo = repoInfo;
        this.issue = issue;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        if (issue == null || issue.title == null) {
            throw new RuntimeException("Issue or Issue title can not be null");
        }
        IssuesService service = restAdapter.create(IssuesService.class);
        service.create(repoInfo.owner, repoInfo.name, issue, this);
    }

    @Override
    public String getAcceptHeader() {
        return "application/vnd.github.v3.text+json";
    }
}
