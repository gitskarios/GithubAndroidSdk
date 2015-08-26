package com.alorma.github.sdk.bean.dto.response;

import java.util.List;

public class GitTree extends ShaUrl {

    public List<GitTreeEntry> tree;
    public boolean truncated;
}
