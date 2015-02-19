package com.alorma.github.sdk.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by Bernat on 08/07/2014.
 */
public class StoreCredentials {

	private static final String USER_TOKEN = StoreCredentials.class.getSimpleName() + ".USER_TOKEN";
	private static final String USER_SCOPES = StoreCredentials.class.getSimpleName() + ".USER_SCOPES";
	private final SharedPreferences.Editor editor;
	private final SharedPreferences preferences;

	public StoreCredentials(Context context) {
		preferences = PreferenceManager.getDefaultSharedPreferences(context);
		editor = preferences.edit();
	}

	public void storeToken(String accessToken) {
		editor.putString(USER_TOKEN, accessToken);
		editor.apply();
	}

	public String token() {
		return preferences.getString(USER_TOKEN, null);
	}

	public String storeScopes(String scopes) {
		return preferences.getString(USER_SCOPES, null);
	}

	public String scopes() {
		return preferences.getString(USER_SCOPES, null);
	}

	public void clear() {
		editor.clear();
		editor.apply();
	}
}
