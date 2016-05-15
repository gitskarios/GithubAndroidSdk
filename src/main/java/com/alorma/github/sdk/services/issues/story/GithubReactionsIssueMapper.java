package com.alorma.github.sdk.services.issues.story;

import com.alorma.github.sdk.bean.dto.response.GithubComment;
import com.alorma.github.sdk.bean.dto.response.GithubCommentReactions;

import rx.functions.Func1;

public class GithubReactionsIssueMapper implements Func1<GithubComment, GithubComment> {
    @Override
    public GithubComment call(GithubComment githubComment) {
        GithubCommentReactions reactions = githubComment.reactions;
        if (reactions != null) {
            try {
                reactions.setTotalCount(((Double) reactions.get("total_count")).intValue());
                reactions.setHeart(((Double) reactions.get("heart")).intValue());
                reactions.setHooray(((Double) reactions.get("hooray")).intValue());
                reactions.setConfused(((Double) reactions.get("confused")).intValue());
                reactions.setPlusOne(((Double) reactions.get("+1")).intValue());
                reactions.setMinusOne(((Double) reactions.get("-1")).intValue());
                reactions.setLaugh(((Double) reactions.get("laugh")).intValue());
                reactions.setUrl((String) reactions.get("url"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return githubComment;
    }
}
