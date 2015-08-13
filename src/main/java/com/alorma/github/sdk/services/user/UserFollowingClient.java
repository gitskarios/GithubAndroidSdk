package com.alorma.github.sdk.services.user;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.User;

import java.util.List;

/**
 * Created by Bernat on 14/07/2014.
 */
public class UserFollowingClient extends GithubUsersClient<List<User>> {

    private String username;
    private int page = 0;

    public UserFollowingClient(Context context, String username) {
        super(context);
        this.username = username;
    }

    public UserFollowingClient(Context context, String username, int page) {
        super(context);
        this.username = username;
        this.page = page;
    }

    @Override
    protected void executeService(UsersService usersService) {
        if (page == 0) {
            if (username == null) {
                usersService.following(this);
            } else {
                usersService.following(username, this);
            }
        } else {
            if (username == null) {
                usersService.following(page, this);
            } else {
                usersService.following(username, page, this);
            }
        }
    }

    @Override
    protected List<User> executeServiceSync(UsersService usersService) {
        if (page == 0) {
            if (username == null) {
                return usersService.following();
            } else {
                return usersService.following(username);
            }
        } else {
            if (username == null) {
                return usersService.following(page);
            } else {
                return usersService.following(username, page);
            }
        }
    }
}
