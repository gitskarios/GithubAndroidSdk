package com.alorma.github.sdk.services.issues;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.Milestone;
import com.alorma.github.sdk.bean.dto.response.MilestoneState;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.BaseInfiniteCallback;
import com.alorma.github.sdk.services.client.GithubClient;
import java.util.List;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 14/04/2015.
 */
public class GetMilestonesClient extends GithubClient<List<Milestone>> {

  private RepoInfo repoInfo;
  private MilestoneState state;

  public GetMilestonesClient(Context context, RepoInfo repoInfo, MilestoneState state) {
    super(context);
    this.repoInfo = repoInfo;
    this.state = state;
  }

  @Override
  protected Observable<List<Milestone>> getApiObservable(final RestAdapter restAdapter) {
    return Observable.create(new BaseInfiniteCallback<List<Milestone>>() {
      @Override
      public void execute() {
        IssuesService issuesService = restAdapter.create(IssuesService.class);
        issuesService.milestones(repoInfo.owner, repoInfo.name, state.name(), this);
      }

      @Override
      protected void executePaginated(int nextPage) {
        IssuesService issuesService = restAdapter.create(IssuesService.class);
        issuesService.milestones(repoInfo.owner, repoInfo.name, state.name(), nextPage, this);
      }
    });
  }
}
