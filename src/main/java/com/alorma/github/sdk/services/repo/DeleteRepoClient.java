package com.alorma.github.sdk.services.repo;

import android.content.Context;
import com.alorma.github.sdk.bean.info.RepoInfo;
import retrofit.RestAdapter;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by a557114 on 01/08/2015.
 */
public class DeleteRepoClient extends GithubRepoClient<Response> {

  public DeleteRepoClient(Context context, RepoInfo repoInfo) {
    super(context, repoInfo);
  }

  @Override
  protected Observable<Response> getApiObservable(RestAdapter restAdapter) {
    return restAdapter.create(RepoService.class).delete(getOwner(), getRepo());
  }
}
