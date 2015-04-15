package com.alorma.github.sdk.services.issues;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.request.EditIssueRequestDTO;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.services.client.BaseClient;

import retrofit.RestAdapter;

/**
 * Created by Bernat on 15/04/2015.
 */
public class EditIssueClient extends BaseClient<Issue> {
    private IssueInfo info;
    private EditIssueRequestDTO editIssueRequestDTO;

    public EditIssueClient(Context context, IssueInfo info, EditIssueRequestDTO editIssueRequestDTO) {
        super(context);
        this.info = info;
        this.editIssueRequestDTO = editIssueRequestDTO;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        restAdapter.create(IssuesService.class).editIssue(info.repo.owner, info.repo.name, info.num, editIssueRequestDTO, this);
    }
}
