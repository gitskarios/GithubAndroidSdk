package com.alorma.github.sdk.bean.dto.response;

import java.util.List;

/**
 * Created by a557114 on 31/07/2015.
 */
public class CompareCommit {
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
    /*
    {
  "files": [
    {
      "sha": "bbcd538c8e72b8c175046e27cc8f907076331401",
      "filename": "file1.txt",
      "status": "added",
      "additions": 103,
      "deletions": 21,
      "changes": 124,
      "blob_url": "https://github.com/octocat/Hello-World/blob/6dcb09b5b57875f334f61aebed695e2e4193db5e/file1.txt",
      "raw_url": "https://github.com/octocat/Hello-World/raw/6dcb09b5b57875f334f61aebed695e2e4193db5e/file1.txt",
      "contents_url": "https://api.github.com/repos/octocat/Hello-World/contents/file1.txt?ref=6dcb09b5b57875f334f61aebed695e2e4193db5e",
      "patch": "@@ -132,7 +132,7 @@ module Test @@ -1000,7 +1000,7 @@ module Test"
    }
  ]
}
     */
}
