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
import com.alorma.github.sdk.bean.issue.IssueStoryComparators;
import com.alorma.github.sdk.bean.issue.IssueStoryEvent;
import com.alorma.github.sdk.bean.issue.IssueStory;
import com.alorma.github.sdk.bean.issue.IssueStoryComment;
import com.alorma.github.sdk.bean.issue.IssueStoryDetail;
import com.alorma.github.sdk.bean.issue.IssueStoryLabelList;
import com.alorma.github.sdk.bean.issue.IssueStoryUnlabelList;
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
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Bernat on 07/04/2015.
 */
public class IssueStoryLoader extends GithubClient<IssueStory> {

  private final IssueInfo issueInfo;
  private final String owner;
  private final String repo;
  private final int num;
  private List<IssueStoryDetail> storyDetailMap;
  private IssueStory issueStory;
  private IssueStoryService issueStoryService;

  public IssueStoryLoader(Context context, IssueInfo info) {
    super(context);
    this.issueInfo = info;
    this.owner = issueInfo.repoInfo.owner;
    this.repo = issueInfo.repoInfo.name;
    this.num = issueInfo.num;
  }

  @Override
  public Observable<Pair<IssueStory, Response>> observable() {
    issueStoryService = getRestAdapter().create(IssueStoryService.class);

    issueStory = new IssueStory();
    storyDetailMap = new ArrayList<>();

    Observable<List<GithubComment>> commentsObs =
        Observable.create(new Observable.OnSubscribe<List<GithubComment>>() {
          @Override
          public void call(final Subscriber<? super List<GithubComment>> subscriber) {
            new BaseInfiniteCallback<List<GithubComment>>() {

              List<GithubComment> comments;

              @Override
              public void execute() {
                issueStoryService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name,
                    issueInfo.num, this);
              }

              @Override
              protected void executePaginated(int nextPage) {
                issueStoryService.comments(issueInfo.repoInfo.owner, issueInfo.repoInfo.name,
                    issueInfo.num, nextPage, this);
              }

              @Override
              protected void executeNext() {
                subscriber.onNext(comments);
                subscriber.onCompleted();
              }

              @Override
              protected void response(List<GithubComment> githubComments) {
                if (comments == null) {
                  comments = githubComments;
                } else {
                  comments.addAll(githubComments);
                }
              }
            }.execute();
          }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    Observable<List<IssueStoryDetail>> commentsObsDetails =
        commentsObs.flatMap(new Func1<List<GithubComment>, Observable<List<IssueStoryDetail>>>() {
          @Override
          public Observable<List<IssueStoryDetail>> call(List<GithubComment> githubComments) {
            return Observable.from(githubComments)
                .map(new Func1<GithubComment, IssueStoryDetail>() {
                  @Override
                  public IssueStoryDetail call(GithubComment githubComment) {
                    long time = getMilisFromDateClearDay(githubComment.created_at);
                    IssueStoryComment detail = new IssueStoryComment(githubComment);
                    detail.created_at = time;
                    return detail;
                  }
                })
                .toList();
          }
        });

    Observable<Issue> issueObs = issueStoryService.detailObs(owner, repo, num)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());

    Observable<Pair<IssueStory, Response>> map1 = Observable.zip(issueObs, commentsObsDetails,
        new Func2<Issue, List<IssueStoryDetail>, IssueStory>() {
          @Override
          public IssueStory call(Issue issue, List<IssueStoryDetail> githubComments) {
            IssueStory issueStory = new IssueStory();
            issueStory.issue = issue;
            if (issueStory.details == null) {
              issueStory.details = new ArrayList<>();
            }
            issueStory.details.addAll(githubComments);
            return issueStory;
          }
        }).map(new Func1<IssueStory, Pair<IssueStory, Response>>() {
      @Override
      public Pair<IssueStory, Response> call(IssueStory issueStory) {
        return new Pair<>(issueStory, null);
      }
    });

    Observable<List<IssueEvent>> eventsObs =
        Observable.create(new Observable.OnSubscribe<List<IssueEvent>>() {
          @Override
          public void call(final Subscriber<? super List<IssueEvent>> subscriber) {
            new BaseInfiniteCallback<List<IssueEvent>>() {

              List<IssueEvent> events;

              @Override
              public void execute() {
                issueStoryService.events(issueInfo.repoInfo.owner, issueInfo.repoInfo.name,
                    issueInfo.num, this);
              }

              @Override
              protected void executePaginated(int nextPage) {
                issueStoryService.events(issueInfo.repoInfo.owner, issueInfo.repoInfo.name,
                    issueInfo.num, nextPage, this);
              }

              @Override
              protected void executeNext() {
                subscriber.onNext(events);
                subscriber.onCompleted();
              }

              @Override
              protected void response(List<IssueEvent> issueEvents) {
                if (events == null) {
                  events = issueEvents;
                } else {
                  events.addAll(issueEvents);
                }
              }
            }.execute();
          }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    Observable<List<IssueStoryDetail>> eventsObsDetails =
        eventsObs.flatMap(new Func1<List<IssueEvent>, Observable<List<IssueStoryDetail>>>() {
          @Override
          public Observable<List<IssueStoryDetail>> call(final List<IssueEvent> issueEvents) {
            return Observable.from(issueEvents).map(new Func1<IssueEvent, IssueStoryDetail>() {
              @Override
              public IssueStoryDetail call(IssueEvent issueEvent) {
                long time = getMilisFromDateClearDay(issueEvent.created_at);
                IssueStoryEvent detail = new IssueStoryEvent(issueEvent);
                detail.created_at = time;
                return detail;
              }
            }).filter(new Func1<IssueStoryDetail, Boolean>() {
              @Override
              public Boolean call(IssueStoryDetail issueStoryDetail) {
                return validEvent(issueStoryDetail.getType());
              }
            }).toList();
          }
        });

    Observable<Pair<IssueStory, Response>> map2 = Observable.zip(map1, eventsObsDetails,
        new Func2<Pair<IssueStory, Response>, List<IssueStoryDetail>, Pair<IssueStory, Response>>() {
          @Override
          public Pair<IssueStory, Response> call(Pair<IssueStory, Response> issueStoryResponsePair,
              List<IssueStoryDetail> issueStoryDetails) {
            if (issueStoryResponsePair.first.details == null) {
              issueStoryResponsePair.first.details = new ArrayList<>();
            }
            issueStoryResponsePair.first.details.addAll(issueStoryDetails);

            return issueStoryResponsePair;
          }
        });

    return map2.map(new Func1<Pair<IssueStory, Response>, Pair<IssueStory, Response>>() {
      @Override
      public Pair<IssueStory, Response> call(Pair<IssueStory, Response> issueStoryResponsePair) {
        parseIssueStoryDetails(issueStoryResponsePair.first.details);
        return issueStoryResponsePair;
      }
    });
  }

  @Override
  protected void executeService(RestAdapter restAdapter) {
    IssueStory issueStory = executeServiceSync(restAdapter);
    if (getOnResultCallback() != null) {
      getOnResultCallback().onResponseOk(issueStory, null);
    }
  }

  @Override
  protected IssueStory executeServiceSync(RestAdapter restAdapter) {
    return observable().toBlocking().single().first;
  }

  private void parseIssueStoryDetails(List<IssueStoryDetail> details) {
    Collections.sort(details, IssueStoryComparators.ISSUE_STORY_DETAIL_COMPARATOR);
  }

  private long getMilisFromDateClearDay(String createdAt) {
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    DateTime dt = formatter.parseDateTime(createdAt);

    return dt.minuteOfDay().roundFloorCopy().getMillis();
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
