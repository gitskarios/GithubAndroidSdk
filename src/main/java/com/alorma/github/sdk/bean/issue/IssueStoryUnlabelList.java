package com.alorma.github.sdk.bean.issue;

import com.alorma.github.sdk.bean.dto.response.Label;
import com.alorma.github.sdk.bean.dto.response.User;

import java.util.ArrayList;

/**
 * Created by Bernat on 18/07/2015.
 */
public class IssueStoryUnlabelList extends ArrayList<Label> implements IssueStoryDetail {

    public long created_at;
    public User user;

    @Override
    public boolean isList() {
        return true;
    }

    @Override
    public String getType() {
        return "unlabeled";
    }

    @Override
    public long createdAt() {
        return created_at;
    }

    @Override
    public User user() {
        return user;
    }
}
