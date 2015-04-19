package com.alorma.github.sdk.bean.dto.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernat on 20/07/2014.
 */
public class ListIssues extends ArrayList<Issue>{
	public ListIssues() {

	}
	public ListIssues(List<Issue> issues) {
		super(issues);
	}
}
