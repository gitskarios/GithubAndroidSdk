package com.alorma.github.sdk.services.pullrequest.story;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.alorma.github.sdk.PullRequest;
import com.alorma.github.sdk.bean.dto.response.Commit;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.Label;
import com.alorma.github.sdk.bean.dto.response.ReviewComment;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.info.PaginationLink;
import com.alorma.github.sdk.bean.info.RelType;
import com.alorma.github.sdk.bean.issue.IssueEvent;
import com.alorma.github.sdk.bean.issue.IssueStoryComment;
import com.alorma.github.sdk.bean.issue.IssueStoryCommit;
import com.alorma.github.sdk.bean.issue.IssueStoryDetail;
import com.alorma.github.sdk.bean.issue.IssueStoryEvent;
import com.alorma.github.sdk.bean.issue.IssueStoryReviewComment;
import com.alorma.github.sdk.bean.issue.ListIssueEvents;
import com.alorma.github.sdk.bean.issue.PullRequestStory;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.issues.story.IssueStoryService;
import com.alorma.github.sdk.services.pullrequest.PullRequestsService;

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
        PullRequestsService pullRequestsService = adapter.create(PullRequestsService.class);

        pullRequestStory = new PullRequestStory();
        storyDetailMap = new HashMap<>();

        new PullRequestCallback(issueInfo, pullRequestStoryService, issueStoryService, pullRequestsService).execute();
    }

    private class PullRequestCallback extends BaseInfiniteCallback<PullRequest> {

        private final IssueInfo info;
        private final PullRequestStoryService pullRequestStoryService;
        private final IssueStoryService issueStoryService;
        private final PullRequestsService pullRequestsService;

        public PullRequestCallback(IssueInfo info, PullRequestStoryService pullRequestStoryService, IssueStoryService issueStoryService, PullRequestsService pullRequestsService) {
            this.info = info;
            this.pullRequestStoryService = pullRequestStoryService;
            this.issueStoryService = issueStoryService;
            this.pullRequestsService = pullRequestsService;
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
            new LabelsCallback(issueInfo, issueStoryService, pullRequestsService).execute();
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
        private final PullRequestsService pullRequestsService;

        public LabelsCallback(IssueInfo info, IssueStoryService issueStoryService, PullRequestsService pullRequestsService) {
            this.info = info;
            this.issueStoryService = issueStoryService;
            this.pullRequestsService = pullRequestsService;
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
            new IssueCommentsCallback(issueInfo, issueStoryService, pullRequestsService).execute();
        }

        @Override
        protected void response(List<Label> issueLabels) {
            pullRequestStory.pullRequest.labels.addAll(issueLabels);
        }
    }

    @Override
    public String getAcceptHeader() {
        return "application/vnd.github.v3.full+json";
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

    private class IssueCommentsCallback extends BaseInfiniteCallback<List<GithubComment>> {

        private final IssueInfo info;
        private final IssueStoryService issueStoryService;
        private final PullRequestsService pullRequestsService;

        public IssueCommentsCallback(IssueInfo info, IssueStoryService issueStoryService, PullRequestsService pullRequestsService) {
            this.info = info;
            this.issueStoryService = issueStoryService;
            this.pullRequestsService = pullRequestsService;
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
            new IssueEventsCallbacks(info, issueStoryService, pullRequestsService).execute();
        }

        @Override
        protected void response(List<GithubComment> issueComments) {
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

    private class IssueEventsCallbacks extends BaseInfiniteCallback<List<IssueEvent>> {

        private IssueInfo info;
        private IssueStoryService issueStoryService;
        private PullRequestsService pullRequestsService;

        public IssueEventsCallbacks(IssueInfo info, IssueStoryService issueStoryService, PullRequestsService pullRequestsService) {
            this.info = info;
            this.issueStoryService = issueStoryService;
            this.pullRequestsService = pullRequestsService;
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
            new IssueReviewCommentsCallbacks(info, pullRequestsService).execute();
        }

        @Override
        protected void response(List<IssueEvent> issueEvents) {
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

    private class IssueReviewCommentsCallbacks extends BaseInfiniteCallback<List<ReviewComment>> {

        private IssueInfo info;
        private PullRequestsService pullRequestsService;

        public IssueReviewCommentsCallbacks(IssueInfo info, PullRequestsService pullRequestsService) {
            this.info = info;
            this.pullRequestsService = pullRequestsService;
        }

        @Override
        public void execute() {
            pullRequestsService.reviewComments(info.repoInfo.owner, info.repoInfo.name, info.num, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            pullRequestsService.reviewComments(info.repoInfo.owner, info.repoInfo.name, info.num, nextPage, this);
        }

        @Override
        protected void executeNext() {
            new PullCommitsCallbacks(info, pullRequestsService).execute();
        }

        @Override
        protected void response(List<ReviewComment> reviewComments) {
            for (ReviewComment reviewComment : reviewComments) {
                long time = getMilisFromDate(reviewComment.created_at);
                List<IssueStoryDetail> details = storyDetailMap.get(time);
                if (details == null) {
                    details = new ArrayList<>();
                    storyDetailMap.put(time, details);
                }
                details.add(new IssueStoryReviewComment(reviewComment));
            }
        }
    }

    private class PullCommitsCallbacks extends BaseInfiniteCallback<List<Commit>> {

        private IssueInfo info;
        private PullRequestsService pullRequestsService;

        public PullCommitsCallbacks(IssueInfo info, PullRequestsService pullRequestsService) {
            this.info = info;
            this.pullRequestsService = pullRequestsService;
        }

        @Override
        public void execute() {
            pullRequestsService.commits(info.repoInfo.owner, info.repoInfo.name, info.num, this);
        }

        @Override
        protected void executePaginated(int nextPage) {
            pullRequestsService.commits(info.repoInfo.owner, info.repoInfo.name, info.num, nextPage, this);
        }

        @Override
        protected void executeNext() {
            parseIssueStoryDetails();
        }

        @Override
        protected void response(List<Commit> commits) {
            for (Commit commit : commits) {
                if (commit.committer != null) {
                    String date = commit.commit.committer.date;
                    if (date != null) {
                        long time = getMilisFromDate(date);
                        List<IssueStoryDetail> commitsDetails = storyDetailMap.get(time);
                        if (commitsDetails == null) {
                            commitsDetails = new ArrayList<>();
                            storyDetailMap.put(time, commitsDetails);
                        }
                        commitsDetails.add(new IssueStoryCommit(commit));
                    }
                }
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
