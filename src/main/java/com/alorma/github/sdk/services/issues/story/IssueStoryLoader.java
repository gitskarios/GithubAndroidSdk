package com.alorma.github.sdk.services.issues.story;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import android.util.Pair;
import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.Issue;
import com.alorma.github.sdk.bean.info.IssueInfo;
import com.alorma.github.sdk.bean.issue.IssueEvent;
import com.alorma.github.sdk.bean.issue.IssueStoryComparators;
import com.alorma.github.sdk.bean.issue.IssueStoryEvent;
import com.alorma.github.sdk.bean.issue.IssueStory;
import com.alorma.github.sdk.bean.issue.IssueStoryComment;
import com.alorma.github.sdk.bean.issue.IssueStoryDetail;
import com.alorma.github.sdk.services.client.GithubClient;

import java.util.ArrayList;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Collections;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.client.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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
  private IssueStoryService issueStoryService;

  public IssueStoryLoader(Context context, IssueInfo info) {
    super(context);
    this.issueInfo = info;
    this.owner = issueInfo.repoInfo.owner;
    this.repo = issueInfo.repoInfo.name;
    this.num = issueInfo.num;
    issueStoryService = getRestAdapter().create(IssueStoryService.class);
  }

  public Observable<IssueStory> create() {
    return getIssueStory().observeOn(AndroidSchedulers.mainThread());
  }

  @Override
  public Observable<Pair<IssueStory, Response>> observable() {
    return create().map(new Func1<IssueStory, Pair<IssueStory, Response>>() {
      @Override
      public Pair<IssueStory, Response> call(IssueStory issueStory) {
        return new Pair<>(issueStory, null);
      }
    }).observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull
  private Observable<IssueStory> getIssueStory() {
    return Observable.zip(getIssueObservable(), getIssueDetailsObservable(),
        new Func2<Issue, List<IssueStoryDetail>, IssueStory>() {
          @Override
          public IssueStory call(Issue issue, List<IssueStoryDetail> details) {
            IssueStory issueStory = new IssueStory();
            issueStory.issue = issue;
            issueStory.details = details;
            return issueStory;
          }
        });
  }

  private Observable<Issue> getIssueObservable() {
    return issueStoryService.detailObs(owner, repo, num).subscribeOn(Schedulers.io());
  }

  private Observable<List<IssueStoryDetail>> getIssueDetailsObservable() {
    return Observable.merge(getCommentsDetailsObs().subscribeOn(Schedulers.computation()),
        getEventDetailsObs().doOnNext(new Action1<List<IssueStoryDetail>>() {
          @Override
          public void call(List<IssueStoryDetail> details) {
            Collections.sort(details, IssueStoryComparators.ISSUE_STORY_DETAIL_COMPARATOR);
          }
        }).subscribeOn(Schedulers.computation()));
  }

  @NonNull
  private Observable<List<GithubComment>> getCommentsObs() {
    return Observable.create(new Observable.OnSubscribe<List<GithubComment>>() {
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
    });
  }

  private Observable<List<IssueStoryDetail>> getCommentsDetailsObs() {
    return getCommentsObs().subscribeOn(Schedulers.io())
        .flatMap(new Func1<List<GithubComment>, Observable<List<IssueStoryDetail>>>() {
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
  }

  @NonNull
  private Observable<List<IssueEvent>> getEventsObs() {
    return Observable.create(new Observable.OnSubscribe<List<IssueEvent>>() {
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
    });
  }

  @NonNull
  private Observable<List<IssueStoryDetail>> getEventDetailsObs() {
    return getEventsObs().subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .flatMap(new Func1<List<IssueEvent>, Observable<List<IssueStoryDetail>>>() {
          @Override
          public Observable<List<IssueStoryDetail>> call(List<IssueEvent> issueEvents) {
            return Observable.from(issueEvents).filter(new Func1<IssueEvent, Boolean>() {
              @Override
              public Boolean call(IssueEvent issueEvent) {
                return validEvent(issueEvent.event);
              }
            }).map(new Func1<IssueEvent, IssueStoryDetail>() {
              @Override
              public IssueStoryDetail call(IssueEvent issueEvent) {
                long time = getMilisFromDateClearDay(issueEvent.created_at);
                IssueStoryEvent detail = new IssueStoryEvent(issueEvent);
                detail.created_at = time;
                return detail;
              }
            }).toList();
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

  private long getMilisFromDateClearDay(String createdAt) {
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    DateTime dt = formatter.parseDateTime(createdAt);

    return dt.minuteOfDay().roundFloorCopy().getMillis();
  }

  private boolean validEvent(String event) {
    return true;
    /*
    return !(event.equals("mentioned") ||
        event.equals("subscribed") ||
        event.equals("unsubscribed"));
        */
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
