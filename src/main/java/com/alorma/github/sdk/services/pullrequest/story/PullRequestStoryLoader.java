package com.alorma.github.sdk.services.pullrequest.story;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.alorma.github.sdk.PullRequest;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.Label;
import com.alorma.github.sdk.bean.dto.response.ListIssueComments;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.info.PaginationLink;
import com.alorma.github.sdk.bean.info.RelType;
import com.alorma.github.sdk.bean.issue.IssueEvent;
import com.alorma.github.sdk.bean.issue.IssueStoryComment;
import com.alorma.github.sdk.bean.issue.IssueStoryDetail;
import com.alorma.github.sdk.bean.issue.IssueStoryEvent;
import com.alorma.github.sdk.bean.issue.ListIssueEvents;
import com.alorma.github.sdk.bean.issue.PullRequestStory;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.issues.story.IssueStoryService;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
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
public class PullRequestStoryLoader extends GithubClient<PullRequestStory> {

    private final IssueInfo issueInfo;
    private Map<Long, List<IssueStoryDetail>> storyDetailMap;
    private PullRequestStory pullRequestStory;

    public PullRequestStoryLoader(Context context, IssueInfo info) {
        super(context);
        this.issueInfo = info;
    }

    @Override
    protected void executeService(RestAdapter adapter) {
        PullRequestStoryService pullRequestStoryService = adapter.create(PullRequestStoryService.class);
        IssueStoryService issueStoryService = adapter.create(IssueStoryService.class);

        pullRequestStory = new PullRequestStory();
        storyDetailMap = new HashMap<>();

        new PullRequestCallback(issueInfo, pullRequestStoryService, issueStoryService).execute();
    }

    private class PullRequestCallback extends BaseInfiniteCallback<PullRequest> {

        private final IssueInfo info;
        private final PullRequestStoryService pullRequestStoryService;
        private IssueStoryService issueStoryService;

        public PullRequestCallback(IssueInfo info, PullRequestStoryService pullRequestStoryService, IssueStoryService issueStoryService) {
            this.info = info;
            this.pullRequestStoryService = pullRequestStoryService;
            this.issueStoryService = issueStoryService;
        }

        @Override
        public void execute() {
            pullRequestStoryService.detail(info.repoInfo.owner, info.repoInfo.name, info.num, this);
        }

        @Override
        protected void executePaginated(int nextPage) {

        }

        @Override
        protected void executeNext() {
            new LabelsCallback(issueInfo, issueStoryService).execute();
        }

        @Override
        protected void response(PullRequest pullRequest) {
            pullRequestStory.pullRequest = pullRequest;
            pullRequestStory.pullRequest.labels = new ArrayList<>();
        }

    }

    private class LabelsCallback extends BaseInfiniteCallback<List<Label>> {

        private final IssueInfo info;
        private final IssueStoryService issueStoryService;

        public LabelsCallback(IssueInfo info, IssueStoryService issueStoryService) {
            this.info = info;
            this.issueStoryService = issueStoryService;
        }


        @Override
        public void execute() {
            issueStoryService.labels(info.repoInfo.owner, info.repoInfo.name, info.num, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            issueStoryService.labels(info.repoInfo.owner, info.repoInfo.name, info.num, nextPage, this);
        }

        @Override
        protected void executeNext() {
            new IssueCommentsCallback(issueInfo, issueStoryService).execute();
        }

        @Override
        protected void response(List<Label> issueLabels) {
            pullRequestStory.pullRequest.labels.addAll(issueLabels);
        }
    }

    @Override
    public String getAcceptHeader() {
        return "application/vnd.github.v3.html+json";
    }

    private void parseIssueStoryDetails() {
        List<Long> times = new ArrayList<>(storyDetailMap.keySet());

        Collections.sort(times);

        List<Pair<Long, IssueStoryDetail>> details = new ArrayList<>();

        for (Long time : times) {
            List<IssueStoryDetail> detailsRow = storyDetailMap.get(time);
            for (IssueStoryDetail issueStoryDetail : detailsRow) {
                details.add(new Pair<>(time, issueStoryDetail));
            }
        }

        pullRequestStory.details = details;

        if (getOnResultCallback() != null) {
            getOnResultCallback().onResponseOk(pullRequestStory, null);
        }
    }

    private class IssueCommentsCallback extends BaseInfiniteCallback<ListIssueComments> {

        private final IssueInfo info;
        private final IssueStoryService issueStoryService;

        public IssueCommentsCallback(IssueInfo info, IssueStoryService issueStoryService) {
            this.info = info;
            this.issueStoryService = issueStoryService;
        }


        @Override
        public void execute() {
            issueStoryService.comments(info.repoInfo.owner, info.repoInfo.name, info.num, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            issueStoryService.comments(info.repoInfo.owner, info.repoInfo.name, info.num, nextPage, this);
        }

        @Override
        protected void executeNext() {
            new IssueEventsCallbacks(info, issueStoryService).execute();
        }

        @Override
        protected void response(ListIssueComments issueComments) {
            for (GithubComment comment : issueComments) {
                long time = getMilisFromDate(comment.created_at);
                List<IssueStoryDetail> details = storyDetailMap.get(time);
                if (details == null) {
                    details = new ArrayList<>();
                    storyDetailMap.put(time, details);
                }
                details.add(new IssueStoryComment(comment));
            }
        }
    }

    private class IssueEventsCallbacks extends BaseInfiniteCallback<ListIssueEvents> {

        private IssueInfo info;
        private IssueStoryService issueStoryService;

        public IssueEventsCallbacks(IssueInfo info, IssueStoryService issueStoryService) {
            this.info = info;
            this.issueStoryService = issueStoryService;
        }

        @Override
        public void execute() {
            issueStoryService.events(info.repoInfo.owner, info.repoInfo.name, info.num, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            issueStoryService.events(info.repoInfo.owner, info.repoInfo.name, info.num, nextPage, this);
        }

        @Override
        protected void executeNext() {
            parseIssueStoryDetails();
        }

        @Override
        protected void response(ListIssueEvents issueEvents) {
            for (IssueEvent event : issueEvents) {
                if (validEvent(event.event)) {
                    long time = getMilisFromDate(event.created_at);
                    List<IssueStoryDetail> details = storyDetailMap.get(time);
                    if (details == null) {
                        details = new ArrayList<>();
                        storyDetailMap.put(time, details);
                    }
                    details.add(new IssueStoryEvent(event));
                }
            }
        }

        private boolean validEvent(String event) {
            return !(event.equals("mentioned") ||
                    event.equals("subscribed") ||
                    event.equals("unsubscribed"));
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

        @Override
        public void failure(RetrofitError error) {
            if (getOnResultCallback() != null) {
                getOnResultCallback().onFail(error);
            }
        }

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
