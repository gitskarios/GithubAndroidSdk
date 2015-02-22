package com.alorma.github.sdk.bean.dto.response;

public class Commit extends ShaUrl{

    public Commit commit;
    public User author;
    public ListShaUrl parents;
	public GitChangeStatus stats;
    public User committer;
	public String message;
	public boolean distinct;
	public GitCommitFiles files;
	public int days;

	@Override
	public String toString() {
		return "[" + sha + "] " + commit.message;
	}
}