package com.alorma.github.sdk.bean.dto.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by a557114 on 31/07/2015.
 */
public class CompareCommit implements Serializable {
  public String url;
  public String html_url;
  public String permalink_url;
  public String diff_url;
  public String patch_url;
  public Commit base_commit;
  public Commit merge_base_commit;
  public String status;
  public int ahead_by;
  public int behind_by;
  public int total_commits;
  public List<Commit> commits;
  public List<CommitFile> files;

}
