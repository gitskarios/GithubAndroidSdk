package com.alorma.github.sdk.services.issues.story;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.dto.response.IssueComment;
import com.alorma.github.sdk.bean.dto.response.ListIssueComments;
import com.alorma.github.sdk.bean.dto.response.ListEvents;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.info.PaginationLink;
import com.alorma.github.sdk.bean.info.RelType;
import com.alorma.github.sdk.bean.issue.IssueEvent;
import com.alorma.github.sdk.bean.issue.IssueEventComment;
import com.alorma.github.sdk.bean.issue.IssueStory;
import com.alorma.github.sdk.bean.issue.IssueStoryComment;
import com.alorma.github.sdk.bean.issue.IssueStoryDetail;
import com.alorma.github.sdk.bean.issue.ListIssueEvents;
import com.alorma.github.sdk.services.client.BaseClient;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStoryLoader extends BaseClient<IssueStory> {

    private final IssueInfo issueInfo;
    private Map<Long, List<IssueStoryDetail>> storyDetailMap;
    private IssueStory issueStory;

    public IssueStoryLoader(Context context, IssueInfo info) {
        super(context);
        this.issueInfo = info;
    }

    @Override
    protected void executeService(RestAdapter adapter) {

        IssueStoryService issueStoryService = adapter.create(IssueStoryService.class);

        issueStory = new IssueStory();
        storyDetailMap = new HashMap<>();

        new IssueCallback(issueInfo, issueStoryService).execute();
    }

    private void parseIssueStoryDetails() {
        List<Long> times = new ArrayList<>(storyDetailMap.keySet());

        Collections.sort(times);

        List<IssueStoryDetail> details = new ArrayList<>();

        for (Long time : times) {
            List<IssueStoryDetail> detailsRow = storyDetailMap.get(time);
            for (IssueStoryDetail issueStoryDetail : detailsRow) {
                details.add(issueStoryDetail);
            }
        }

        issueStory.details = details;

        if (getOnResultCallback() != null) {
            getOnResultCallback().onResponseOk(issueStory, null);
        }
    }

    private class IssueCallback extends BaseCallback<Issue> {

        public IssueCallback(IssueInfo info, IssueStoryService issueStoryService) {
            super(info, issueStoryService);
        }

        @Override
        public void execute() {
            issueStoryService.detail(info.repo.owner, info.repo.name, info.num, this);
        }

        @Override
        protected void executePaginated(int nextPage) {

        }

        @Override
        protected void executeNext() {
            new IssueCommentsCallback(info, issueStoryService).execute();
        }

        @Override
        protected void response(Issue issue) {
            issueStory.issue = issue;
        }

        @Override
        public void failure(RetrofitError error) {

        }
    }

    private class IssueCommentsCallback extends BaseCallback<ListIssueComments> {

        public IssueCommentsCallback(IssueInfo info, IssueStoryService issueStoryService) {
            super(info, issueStoryService);
        }


        @Override
        public void execute() {
            issueStoryService.comments(info.repo.owner, info.repo.name, info.num, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            issueStoryService.comments(info.repo.owner, info.repo.name, info.num, nextPage, this);
        }

        @Override
        protected void executeNext() {
            new IssueEventsCallbacks(info, issueStoryService).execute();
        }

        @Override
        protected void response(ListIssueComments issueComments) {
            for (IssueComment comment : issueComments) {
                long time = getMilisFromDate(comment.createdAt);
                List<IssueStoryDetail> details = storyDetailMap.get(time);
                if (details == null) {
                    details = new ArrayList<>();
                    storyDetailMap.put(time, details);
                }
                details.add(new IssueStoryComment(comment));
            }
        }

        @Override
        public void failure(RetrofitError error) {

        }
    }

    private class IssueEventsCallbacks extends BaseCallback<ListIssueEvents> {

        public IssueEventsCallbacks(IssueInfo info, IssueStoryService issueStoryService) {
            super(info, issueStoryService);
        }

        @Override
        public void execute() {
            issueStoryService.events(info.repo.owner, info.repo.name, info.num, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            issueStoryService.events(info.repo.owner, info.repo.name, info.num, nextPage, this);
        }

        @Override
        protected void executeNext() {
            parseIssueStoryDetails();
        }

        @Override
        protected void response(ListIssueEvents issueEvents) {
            for (IssueEvent event : issueEvents) {
                long time = getMilisFromDate(event.created_at);
                List<IssueStoryDetail> details = storyDetailMap.get(time);
                if (details == null) {
                    details = new ArrayList<>();
                    storyDetailMap.put(time, details);
                }
                details.add(new IssueEventComment(event));
            }
        }

        @Override
        public void failure(RetrofitError error) {

        }
    }

    private abstract class BaseCallback<T> implements Callback<T> {

        public IssueInfo info;
        public IssueStoryService issueStoryService;

        public BaseCallback(IssueInfo info, IssueStoryService issueStoryService) {
            this.info = info;
            this.issueStoryService = issueStoryService;
        }

        @Override
        public void success(T t, Response response) {
            int nextPage = getLinkData(response);
            response(t);
            if (nextPage != -1) {
                executePaginated(nextPage);
            } else {
                executeNext();
            }
        }

        protected abstract void executePaginated(int nextPage);

        protected abstract void executeNext();

        protected abstract void response(T t);

        private int getLinkData(Response r) {
            List<Header> headers = r.getHeaders();
            Map<String, String> headersMap = new HashMap<String, String>(headers.size());
            for (Header header : headers) {
                headersMap.put(header.getName(), header.getValue());
            }

            String link = headersMap.get("Link");

            if (link != null) {
                String[] parts = link.split(",");
                try {
                    PaginationLink bottomPaginationLink = new PaginationLink(parts[0]);
                    if (bottomPaginationLink.rel == RelType.next) {
                        return bottomPaginationLink.page;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return -1;
        }

        public abstract void execute();
    }

    private long getMilisFromDate(String createdAt) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        DateTime dt = formatter.parseDateTime(createdAt);

        return dt.getMillis();
    }

    @Override
    public void log(String message) {
        Log.i("IssueStoryLoader", message);
    }
}
