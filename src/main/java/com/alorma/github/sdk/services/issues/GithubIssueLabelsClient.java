package com.alorma.github.sdk.services.issues;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.Label;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.BaseInfiniteCallback;
import com.alorma.github.sdk.services.client.GithubClient;
import java.util.List;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 10/05/2015.
 */
public class GithubIssueLabelsClient extends GithubClient<List<Label>> {
    private RepoInfo repoInfo;

    public GithubIssueLabelsClient(Context context, RepoInfo repoInfo) {
        super(context);
        this.repoInfo = repoInfo;
    }

    @Override
    protected Observable<List<Label>> getApiObservable(final RestAdapter restAdapter) {
        return Observable.create(new BaseInfiniteCallback<List<Label>>() {
            @Override
            public void execute() {
                IssuesService issueService = restAdapter.create(IssuesService.class);
                issueService.labels(repoInfo.owner, repoInfo.name, this);
            }

            @Override
            protected void executePaginated(int nextPage) {
                IssuesService issueService = restAdapter.create(IssuesService.class);
                issueService.labels(repoInfo.owner, repoInfo.name, nextPage, this);
            }
        });
    }
}
