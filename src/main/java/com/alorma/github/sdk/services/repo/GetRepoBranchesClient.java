package com.alorma.github.sdk.services.repo;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.Branch;
import com.alorma.github.sdk.bean.info.RepoInfo;
import java.util.List;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetRepoBranchesClient extends GithubRepoClient<List<Branch>> {

  public GetRepoBranchesClient(RepoInfo repoInfo) {
    super(repoInfo);
  }

  @Override
  protected Observable<List<Branch>> getApiObservable(RestAdapter restAdapter) {
    return restAdapter.create(RepoService.class).branches(getOwner(), getRepo());
  }
}
