package com.alorma.github.sdk.services.repos;

public class UserReposClient extends GithubReposClient {

  public UserReposClient() {
    super();
  }

  public UserReposClient(String username, String sort) {
    super(username, sort);
  }

  public UserReposClient(String username, String sort, int page) {
    super(username, sort, page);
  }

  @Override
  protected void executeUserFirstPage(String sort, ReposService usersService, ApiSubscriber apiSubscriber) {
    usersService.userReposList(sort, apiSubscriber);
  }

  @Override
  protected void executeFirstPageByUsername(String username, String sort, ReposService usersService, ApiSubscriber apiSubscriber) {
    usersService.userReposList(username, sort, apiSubscriber);
  }

  @Override
  protected void executeUserPaginated(int page, String sort, ReposService usersService, ApiSubscriber apiSubscriber) {
    usersService.userReposList(page, sort, apiSubscriber);
  }

  @Override
  protected void executePaginatedByUsername(String username, int page, String sort, ReposService usersService,
      ApiSubscriber apiSubscriber) {
    usersService.userReposList(username, page, sort, apiSubscriber);
  }

  @Override
  public String getAcceptHeader() {
    return "application/vnd.github.moondragon+json";
  }
}
