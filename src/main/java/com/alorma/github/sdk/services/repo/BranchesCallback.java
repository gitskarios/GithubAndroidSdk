package com.alorma.github.sdk.services.repo;

import com.alorma.github.sdk.bean.dto.response.Branch;
import com.alorma.github.sdk.bean.info.RepoInfo;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;

public abstract class BranchesCallback extends Subscriber<List<Branch>> {

	private RepoInfo repoInfo;
	List<String> names;
	private int selectedIndex = 0;

	public BranchesCallback(RepoInfo repoInfo) {
		this.repoInfo = repoInfo;
		names = new ArrayList<>();
	}


	@Override
	public void onCompleted() {
		showBranches(names.toArray(new String[names.size()]), selectedIndex);
	}

	@Override
	public void onError(Throwable e) {

	}

	@Override
	public void onNext(List<Branch> branches) {
		if (branches != null) {

			String[] names = new String[branches.size()];
			for (int i = 0; i < branches.size(); i++) {
				String branchName = branches.get(i).name;
				names[i] = branchName;
				if ((branchName.equalsIgnoreCase(repoInfo.branch))) {
					selectedIndex = i;
				}
			}
		}
	}

	protected abstract void showBranches(String[] branches, int defaultBranchPosition);
}