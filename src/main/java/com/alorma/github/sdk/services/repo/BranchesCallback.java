package com.alorma.github.sdk.services.repo;

import com.alorma.github.sdk.bean.dto.response.ListBranches;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.GithubClient;

import retrofit.client.Response;

public abstract class BranchesCallback implements GithubClient.OnResultCallback<ListBranches> {

	private RepoInfo repoInfo;

	public BranchesCallback(RepoInfo repoInfo) {
		this.repoInfo = repoInfo;
	}

	@Override
	public void onResponseOk(ListBranches branches, Response r) {
		if (branches != null) {
			String[] names = new String[branches.size()];
			int selectedIndex = 0;
			for (int i = 0; i < branches.size(); i++) {
				String branchName = branches.get(i).name;
				names[i] = branchName;
				if ((branchName.equalsIgnoreCase(repoInfo.branch))) {
					selectedIndex = i;
				}
			}

			showBranches(names, selectedIndex);
		}
	}

	protected abstract void showBranches(String[] branches, int defaultBranchPosition);

	public RepoInfo getRepoInfo() {
		return repoInfo;
	}
}