package com.alorma.github.sdk.utils;

import android.content.Context;

/**
 * Created by Bernat on 26/09/2014.
 */
public class GitskariosSettings extends PreferencesHelper{
	public static final String KEY_REPO_SORT = "KEY_REPO_SORT";

	public GitskariosSettings(Context context) {
		super(context);
	}

	public void saveRepoSort(String value) {
		saveStringSetting(KEY_REPO_SORT, value);
	}

	public String getRepoSort(String defaultValue) {
		return getStringSetting(KEY_REPO_SORT, defaultValue);
	}
}
