package com.alorma.github.sdk.services.gtignore;

import android.content.Context;

import android.util.Pair;
import com.alorma.github.sdk.bean.dto.response.GitIgnoreTemplates;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import retrofit.client.Response;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Bernat on 28/09/2014.
 */
public class GitIgnoreClient extends GithubClient<GitIgnoreTemplates> {
	public GitIgnoreClient(Context context) {
		super(context);
		setApiObservable(getRestAdapter().create(GitIgnoreService.class).listObs());
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		restAdapter.create(GitIgnoreService.class).list(this);
	}

	@Override
	protected GitIgnoreTemplates executeServiceSync(RestAdapter restAdapter) {
		return restAdapter.create(GitIgnoreService.class).list();
	}
}
