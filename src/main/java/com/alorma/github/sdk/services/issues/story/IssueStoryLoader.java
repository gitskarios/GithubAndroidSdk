package com.alorma.github.sdk.services.issues.story;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.GithubEvent;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.info.PaginationLink;
import com.alorma.github.sdk.bean.info.RelType;
import com.alorma.github.sdk.bean.issue.IssueEvent;
import com.alorma.github.sdk.bean.issue.IssueStoryEvent;
import com.alorma.github.sdk.bean.issue.IssueStory;
import com.alorma.github.sdk.bean.issue.IssueStoryComment;
import com.alorma.github.sdk.bean.issue.IssueStoryDetail;
import com.alorma.github.sdk.bean.issue.ListIssueEvents;
import com.alorma.github.sdk.services.client.GithubClient;

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
public class IssueStoryLoader extends GithubClient<IssueStory> {

    private final IssueInfo issueInfo;
    private Map<Long, List<IssueStoryDetail>> storyDetailMap;
    private IssueStory issueStory;

    public IssueStoryLoader(Context context, IssueInfo info) {
        super(context);
        this.issueInfo = info;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {

        IssueStoryService issueStoryService = restAdapter.create(IssueStoryService.class);

        issueStory = new IssueStory();
        storyDetailMap = new HashMap<>();

        new IssueCallback(issueInfo, issueStoryService).execute();
    }

    @Override
    protected IssueStory executeServiceSync(RestAdapter restAdapter) {
        issueStory = new IssueStory();
        storyDetailMap = new HashMap<>();

        int page = 1;
        boolean hasMore;


        IssueStoryService issueStoryService = restAdapter.create(IssueStoryService.class);
        issueStory.issue = issueStoryService.detail(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num);

        while(true){
            List<GithubComment> issueComments = issueStoryService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num, page);
            hasMore = issueComments != null && !issueComments.isEmpty();
            if (!hasMore) break;
            for (GithubComment comment : issueComments) {
                long time = getMilisFromDate(comment.created_at);
                List<IssueStoryDetail> details = storyDetailMap.get(time);
                if (details == null) {
                    details = new ArrayList<>();
                    storyDetailMap.put(time, details);
                }
                details.add(new IssueStoryComment(comment));
            }
            page++;
        }

        page = 1;

        while(true){
            List<IssueEvent> issueEvents = issueStoryService.events(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num, page);
            hasMore = issueEvents != null && !issueEvents.isEmpty();
            if (!hasMore) break;
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
            page++;
        }

        return parseIssueStoryDetails();
    }

    private IssueStory parseIssueStoryDetails() {
        List<Long> times = new ArrayList<>(storyDetailMap.keySet());

        Collections.sort(times);

        List<Pair<Long, IssueStoryDetail>> details = new ArrayList<>();

        for (Long time : times) {
            List<IssueStoryDetail> detailsRow = storyDetailMap.get(time);
            for (IssueStoryDetail issueStoryDetail : detailsRow) {
                details.add(new Pair<>(time, issueStoryDetail));
            }
        }

        issueStory.details = details;

        if (getOnResultCallback() != null) {
            getOnResultCallback().onResponseOk(issueStory, null);
        }

        return issueStory;
    }

    private class IssueCallback extends BaseInfiniteCallback<Issue> {

        private final IssueInfo info;
        private final IssueStoryService issueStoryService;

        public IssueCallback(IssueInfo info, IssueStoryService issueStoryService) {
            this.info = info;
            this.issueStoryService = issueStoryService;
        }

        @Override
        public void execute() {
            issueStoryService.detail(info.repoInfo.owner, info.repoInfo.name, info.num, this);
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

    }

    private class IssueCommentsCallback extends BaseInfiniteCallback<List<GithubComment>> {

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
    }

    private long getMilisFromDate(String createdAt) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        DateTime dt = formatter.parseDateTime(createdAt);

        return dt.getMillis();
    }

    private boolean validEvent(String event) {
        return !(event.equals("mentioned") ||
                event.equals("subscribed") ||
                event.equals("unsubscribed"));
    }

    @Override
    public void log(String message) {
        Log.i("IssueStoryLoader", message);
    }

    @Override
    public String getAcceptHeader() {
        return "application/vnd.github.v3.full+json";
    }
}
