package com.alorma.github.sdk.bean.issue;

import android.util.Pair;

import com.alorma.github.sdk.PullRequest;
import com.alorma.github.sdk.bean.dto.response.Issue;

import java.util.List;

/**
 * Created by Bernat on 07/04/2015.
 */
public class PullRequestStory {
    public PullRequest pullRequest;
    public List<Pair<Long, IssueStoryDetail>> details;
}
