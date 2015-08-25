package com.alorma.github.sdk.services.user;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.Email;

import java.util.List;

/**
 * Created by Bernat on 12/07/2014.
 */
public class UserEmailsClient extends GithubUsersClient<List<Email>> {
    public UserEmailsClient(Context context) {
        super(context);
    }

    @Override
    protected void executeService(UsersService usersService) {
        usersService.userEmails(this);
    }

    @Override
    protected List<Email> executeServiceSync(UsersService usersService) {
        return usersService.userEmails();
    }

}
