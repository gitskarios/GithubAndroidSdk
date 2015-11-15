package com.alorma.github.sdk.services.orgs.teams;

import android.content.Context;
import com.alorma.github.sdk.bean.dto.response.User;
import com.alorma.github.sdk.services.client.GithubListClient;
import java.util.List;
import retrofit.RestAdapter;

public class GetTeamMembersClient extends GithubListClient<List<User>> {

    private final String id;
    private final int page;

    public GetTeamMembersClient(Context context, String id) {
        this(context, id,  0);
    }

    public GetTeamMembersClient(Context context, String id, int page) {
        super(context);
        this.id = id;
        this.page = page;
    }

    @Override
    protected void executeService(RestAdapter restAdapter) {
        TeamsService teamsService = restAdapter.create(TeamsService.class);
        if (page == 0) {
            teamsService.members(this.id, this);
        } else {
            teamsService.members(this.id, page, this);
        }
    }

    @Override
    protected List<User> executeServiceSync(RestAdapter restAdapter) {
        TeamsService teamsService = restAdapter.create(TeamsService.class);
        if (page == 0) {
            return teamsService.members(this.id);
        } else {
            return teamsService.members(this.id, page);
        }
    }
}
