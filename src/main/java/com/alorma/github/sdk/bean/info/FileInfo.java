package com.alorma.github.sdk.bean.info;

import java.io.Serializable;

/**
 * Created by Bernat on 24/05/2015.
 */
public class FileInfo implements Serializable {

  public RepoInfo repoInfo;
  public String path;
  public String content;
  public String name;
  public String head;

}