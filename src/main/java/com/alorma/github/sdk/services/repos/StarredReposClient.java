package com.alorma.github.sdk.services.repos;

/**
 * Created by Bernat on 17/07/2014.
 */
public class StarredReposClient extends GithubReposClient {


  public StarredReposClient() {
    super();
  }

  public StarredReposClient(String username, String sort) {
    super(username, sort);
  }

  public StarredReposClient(String username, String sort, int page) {
    super(username, sort, page);
  }

  @Override
  protected void executeUserFirstPage(String sort, ReposService usersService, ApiSubscriber apiSubscriber) {
    usersService.userStarredReposList(sort, apiSubscriber);
  }

  @Override
  protected void executeFirstPageByUsername(String username, String sort, ReposService usersService, ApiSubscriber apiSubscriber) {
    usersService.userStarredReposList(username, sort, apiSubscriber);
  }

  @Override
  protected void executeUserPaginated(int page, String sort, ReposService usersService, ApiSubscriber apiSubscriber) {
    usersService.userStarredReposList(page, sort, apiSubscriber);
  }

  @Override
  protected void executePaginatedByUsername(String username, int page, String sort, ReposService usersService,
      ApiSubscriber apiSubscriber) {
    usersService.userStarredReposList(username, page, sort, apiSubscriber);
  }
}
