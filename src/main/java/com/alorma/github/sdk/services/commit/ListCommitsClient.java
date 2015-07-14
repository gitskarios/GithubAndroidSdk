package com.alorma.github.sdk.services.commit;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 07/09/2014.
 */
public class ListCommitsClient extends GithubClient<List<Commit>> {
    private RepoInfo info;
    private String path;
    private int page;

    public ListCommitsClient(Context context, RepoInfo info, int page) {
        super(context);
        this.info = info;
        this.page = page;
    }

    public ListCommitsClient(Context context, RepoInfo info, String path, int page) {
        super(context);
        this.info = info;
        this.path = path;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        CommitsService commitsService = restAdapter.create(CommitsService.class);
        if (path == null) {
            if (info.branch == null) {
                if (page == 0) {
                    commitsService.commits(info.owner, info.name, this);
                } else {
                    commitsService.commits(info.owner, info.name, page, this);
                }
            } else {
                if (page == 0) {
                    commitsService.commits(info.owner, info.name, info.branch, this);
                } else {
                    commitsService.commits(info.owner, info.name, page, info.branch, this);
                }
            }
        } else {
            if (info.branch == null) {
                if (page == 0) {
                    commitsService.commitsByPath(info.owner, info.name, path, this);
                } else {
                    commitsService.commitsByPath(info.owner, info.name, path, page, this);
                }
            } else {
                if (page == 0) {
                    commitsService.commitsByPath(info.owner, info.name, path, info.branch, this);
                } else {
                    commitsService.commitsByPath(info.owner, info.name, path, info.branch, page, this);
                }
            }
        }
    }
}
