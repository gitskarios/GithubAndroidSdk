package com.alorma.github.sdk.services.user;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.User;

import java.util.List;

/**
 * Created by Bernat on 14/07/2014.
 */
public class UserFollowersClient extends GithubUsersClient<List<User>> {

    private String username;
    private int page = 0;

    public UserFollowersClient(Context context, String username) {
        super(context);
        this.username = username;
    }

    public UserFollowersClient(Context context, String username, int page) {
        super(context);
        this.username = username;
        this.page = page;
    }

    @Override
    protected void executeService(UsersService usersService) {
        if (page == 0) {
            if (username == null) {
                usersService.followers(this);
            } else {
                usersService.followers(username, this);
            }
        } else {
            if (username == null) {
                usersService.followers(page, this);
            } else {
                usersService.followers(username, page, this);
            }
        }
    }

    @Override
    protected List<User> executeServiceSync(UsersService usersService) {
        if (page == 0) {
            if (username == null) {
                return usersService.followers();
            } else {
                return usersService.followers(username);
            }
        } else {
            if (username == null) {
                return usersService.followers(page);
            } else {
                return usersService.followers(username, page);
            }
        }
    }
}
