package com.alorma.github.sdk.utils;

import android.content.Context;

/**
 * Created by Bernat on 26/09/2014.
 */
public class GitskariosSettings extends PreferencesHelper{
	public static final String KEY_REPO_SORT = "KEY_REPO_SORT";
	public static final String KEY_AUTH_USER = "KEY_AUTH_USER";
	public static final String KEY_DOWNLOAD_FILE_TYPE = "KEY_DOWNLOAD_FILE_TYPE";

	public GitskariosSettings(Context context) {
		super(context);
	}

	public void saveRepoSort(String value) {
		saveStringSetting(KEY_REPO_SORT, value);
	}

	public String getRepoSort(String defaultValue) {
		return getStringSetting(KEY_REPO_SORT, defaultValue);
	}
	public void saveAuthUser(String value) {
		saveStringSetting(KEY_AUTH_USER, value);
	}

	public String getAuthUser(String defaultValue) {
		return getStringSetting(KEY_AUTH_USER, defaultValue);
	}

	public String getDownloadFileType() {
		return getStringSetting(KEY_DOWNLOAD_FILE_TYPE, "zipball");
	}

	public void saveDownloadFileType(String downloadFileType) {
		saveStringSetting(KEY_DOWNLOAD_FILE_TYPE, downloadFileType);
	}
}
