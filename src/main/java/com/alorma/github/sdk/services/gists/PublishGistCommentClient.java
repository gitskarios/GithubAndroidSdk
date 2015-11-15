package com.alorma.github.sdk.services.gists;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.request.CommentRequest;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.RestAdapter;
import rx.Observable;

public class PublishGistCommentClient extends GithubClient<GithubComment> {

    private String id;
    private CommentRequest comment;

    public PublishGistCommentClient(Context context, String id, CommentRequest comment) {
        super(context);
        this.id = id;
        this.comment = comment;
    }

    @Override
    protected Observable<GithubComment> getApiObservable(RestAdapter restAdapter) {
        return restAdapter.create(GistsService.class).publishComment(id, comment);
    }
}
