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
	private static final String USER_SCOPES_NO_ASK = StoreCredentials.class.getSimpleName() + ".USER_SCOPES_NO_ASK";
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

	public void storeScopes(String scopes) {
		editor.putString(USER_SCOPES, scopes);
		editor.commit();
	}

	public String scopes() {
		return preferences.getString(USER_SCOPES, null);
	}

	public void saveScopeNoAsk(boolean scopesNoAsk) {
		editor.putBoolean(USER_SCOPES_NO_ASK, scopesNoAsk);
		editor.commit();
	}

	public Boolean scopeNoAsk() {
		return preferences.getBoolean(USER_SCOPES_NO_ASK, false);
	}

	public void clear() {
		editor.clear();
		editor.commit();
	}
}
