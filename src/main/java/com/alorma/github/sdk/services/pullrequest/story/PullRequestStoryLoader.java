package com.alorma.github.sdk.services.pullrequest.story;

import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.Label;
import com.alorma.github.sdk.bean.dto.response.PullRequest;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.issue.IssueEvent;
import com.alorma.github.sdk.bean.issue.IssueStoryComment;
import com.alorma.github.sdk.bean.issue.IssueStoryComparators;
import com.alorma.github.sdk.bean.issue.IssueStoryDetail;
import com.alorma.github.sdk.bean.issue.IssueStoryEvent;
import com.alorma.github.sdk.bean.issue.PullRequestStory;
import com.alorma.github.sdk.services.client.BaseInfiniteCallback;
import com.alorma.github.sdk.services.client.GithubClient;
import com.alorma.github.sdk.services.issues.story.IssueStoryService;
import java.util.Collections;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Bernat on 07/04/2015.
 */
public class PullRequestStoryLoader extends GithubClient<PullRequestStory> {

  private final IssueInfo issueInfo;
  private final String owner;
  private final String repo;
  private final int num;
  private final IssueStoryService issueStoryService;
  private PullRequestStoryService pullRequestStoryService;

  public PullRequestStoryLoader(IssueInfo info) {
    super();
    this.issueInfo = info;
    this.owner = issueInfo.repoInfo.owner;
    this.repo = issueInfo.repoInfo.name;
    this.num = issueInfo.num;
    pullRequestStoryService = getRestAdapter().create(PullRequestStoryService.class);
    issueStoryService = getRestAdapter().create(IssueStoryService.class);
  }

  @Override
  protected Observable<PullRequestStory> getApiObservable(RestAdapter restAdapter) {
    return getPullrequestStory();
  }

  private Observable<PullRequestStory> getPullrequestStory() {
    return Observable.zip(getPullRequestObs(), getIssueDetailsObservable(),
        (pullRequest, details) -> {
          PullRequestStory pullRequestStory = new PullRequestStory();
          pullRequestStory.item = pullRequest;
          pullRequestStory.details = details;
          Collections.sort(pullRequestStory.details,
              IssueStoryComparators.ISSUE_STORY_DETAIL_COMPARATOR);
          return pullRequestStory;
        });
  }

  private Observable<PullRequest> getPullRequestObs() {
    Observable<PullRequest> pullRequestObservable =
        pullRequestStoryService.detailObs(owner, repo, num);

    return Observable.zip(pullRequestObservable, getLabelsObs(), (pullRequest, labels) -> {
      pullRequest.labels = labels;
      return pullRequest;
    });
  }

  private Observable<List<IssueStoryDetail>> getIssueDetailsObservable() {
    Observable<IssueStoryDetail> commentsDetailsObs = getCommentsDetailsObs();
    Observable<IssueStoryDetail> eventDetailsObs = getEventDetailsObs();
    return Observable.mergeDelayError(commentsDetailsObs, eventDetailsObs).toList();
  }

  private Observable<List<GithubComment>> getCommentsObs() {
    return Observable.create(new BaseInfiniteCallback<List<GithubComment>>() {
      @Override
      public void execute() {
        issueStoryService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num,
            this);
      }

      @Override
      protected void executePaginated(int nextPage) {
        issueStoryService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num,
            nextPage, this);
      }
    });
  }

  private Observable<IssueStoryDetail> getCommentsDetailsObs() {
    return getCommentsObs().flatMap(githubComments -> Observable.from(githubComments)
        .map((Func1<GithubComment, IssueStoryDetail>) githubComment -> {
          long time = getMilisFromDateClearDay(githubComment.created_at);
          IssueStoryComment detail = new IssueStoryComment(githubComment);
          detail.created_at = time;
          return detail;
        }));
  }

  private Observable<List<IssueEvent>> getEventsObs() {
    return Observable.create(new BaseInfiniteCallback<List<IssueEvent>>() {

      @Override
      public void execute() {
        issueStoryService.events(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num,
            this);
      }

      @Override
      protected void executePaginated(int nextPage) {
        issueStoryService.events(issueInfo.repoInfo.owner, issueInfo.repoInfo.name, issueInfo.num,
            nextPage, this);
      }
    });
  }

  private Observable<IssueStoryDetail> getEventDetailsObs() {
    return getEventsObs().flatMap(issueEvents -> Observable.from(issueEvents)
        .filter(issueEvent -> validEvent(issueEvent.event))
        .map((Func1<IssueEvent, IssueStoryDetail>) issueEvent -> {
          long time = getMilisFromDateClearDay(issueEvent.created_at);
          IssueStoryEvent detail = new IssueStoryEvent(issueEvent);
          detail.created_at = time;
          return detail;
        }));
  }

  private Observable<List<Label>> getLabelsObs() {
    return Observable.create(new BaseInfiniteCallback<List<Label>>() {
      @Override
      public void execute() {
        issueStoryService.labels(owner, repo, num, this);
      }

      @Override
      protected void executePaginated(int nextPage) {
        issueStoryService.labels(owner, repo, num, nextPage, this);
      }
    });
  }

  private long getMilisFromDateClearDay(String createdAt) {
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    DateTime dt = formatter.parseDateTime(createdAt);

    return dt.minuteOfDay().roundFloorCopy().getMillis();
  }

  private boolean validEvent(String event) {
    return !(event.equals("mentioned") ||
        event.equals("subscribed") ||
        event.equals("unsubscribed") ||
        event.equals("labeled") ||
        event.equals("unlabeled"));
  }

  @Override
  public String getAcceptHeader() {
    return "application/vnd.github.v3.full+json";
  }
}