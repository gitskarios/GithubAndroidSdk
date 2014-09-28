package com.alorma.github.sdk.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;

/**
 * Created by Bernat on 26/09/2014.
 */
public class PreferencesHelper {
	private static final String FILE_NAME = "gitskarios.settings";
	private final SharedPreferences preferences;
	private final SharedPreferences.Editor editor;

	public PreferencesHelper(Context context) {
		preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
		editor = preferences.edit();
	}

	public void saveStringSetting(String key, String value) {
		editor.putString(key, value).apply();
	}

	public String getStringSetting(String key, String defaultValue) {
		return preferences.getString(key, defaultValue);
	}

	public void registerListener(SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener) {
		preferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
	}
	public void unregisterListener(SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener) {
		preferences.unregisterOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);
	}
}
