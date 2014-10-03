package com.alorma.github.sdk.services.user;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.User;

/**
 * Created by Bernat on 03/10/2014.
 */
public class GetAuthUserClient extends BaseUsersClient<User> {
	public GetAuthUserClient(Context context) {
		super(context);
	}

	@Override
	protected void executeService(UsersService usersService) {
		usersService.me(this);
	}
}
