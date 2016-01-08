package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;
import java.util.Date;

public class GistRevision extends ShaUrl implements Serializable {

  private Date committedAt;

  private GitChangeStatus changeStatus;

  private String version;

  private User user;

  /**
   * @return committedAt
   */
  public Date getCommittedAt() {
    return committedAt;
  }

  /**
   * @return this gist revision
   */
  public GistRevision setCommittedAt(Date committedAt) {
    this.committedAt = committedAt;
    return this;
  }

  /**
   * @return changeStatus
   */
  public GitChangeStatus getChangeStatus() {
    return changeStatus;
  }

  /**
   * @return this gist revision
   */
  public GistRevision setChangeStatus(GitChangeStatus changeStatus) {
    this.changeStatus = changeStatus;
    return this;
  }

  /**
   * @return url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @return this gist revision
   */
  public GistRevision setUrl(String url) {
    this.url = url;
    return this;
  }

  /**
   * @return version
   */
  public String getVersion() {
    return version;
  }

  /**
   * @return this gist revision
   */
  public GistRevision setVersion(String version) {
    this.version = version;
    return this;
  }

  /**
   * @return user
   */
  public User getUser() {
    return user;
  }

  /**
   * @return this gist revision
   */
  public GistRevision setUser(User user) {
    this.user = user;
    return this;
  }
}