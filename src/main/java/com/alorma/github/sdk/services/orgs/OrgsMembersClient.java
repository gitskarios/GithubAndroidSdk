package com.alorma.github.sdk.services.orgs;

import android.content.Context;

import com.alorma.github.sdk.bean.dto.response.ListUsers;
import com.alorma.github.sdk.services.user.BaseUsersClient;
import com.alorma.github.sdk.services.user.UsersService;

/**
 * Created by Bernat on 22/02/2015.
 */
public class OrgsMembersClient extends BaseUsersClient<ListUsers> {

	private final String org;
	private int page = 0;

	public OrgsMembersClient(Context context, String org) {
		super(context);
		this.org = org;
	}

	public OrgsMembersClient(Context context, String org, int page) {
		super(context);
		this.org = org;
		this.page = page;
	}

	@Override
	protected void executeService(UsersService usersService) {
		if (page == 0) {
			usersService.orgMembers(org, this);
		} else {
			usersService.orgMembers(org, page, this);

		}
	}
}
