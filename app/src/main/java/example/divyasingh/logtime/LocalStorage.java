package example.divyasingh.logtime;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LocalStorage {

    public static final String AUTH_TOKEN = "auth_token";
    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String NAME = "name";
    public static final String EMAIL_ID = "email";
    private static LocalStorage sInstance;
    private SharedPreferences mPreferences;

    private LocalStorage(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static LocalStorage getInstance() {
        if (sInstance == null) {
            sInstance = new LocalStorage(LogTimeApp.getInstance());
        }
        return sInstance;
    }

    public String getAuthToken() {
        return mPreferences.getString(AUTH_TOKEN, null);
    }

    public void setAuthToken(String token) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(AUTH_TOKEN, token);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return mPreferences.getBoolean(IS_LOGIN, false);
    }

    public void setLoggedIn(boolean value) {
        mPreferences.edit().putBoolean(IS_LOGIN, value).apply();
    }

    public String getName() {
        return mPreferences.getString(NAME, null);
    }

    public void setName(String name) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(NAME, name);
        editor.apply();
    }

    public String getEmail() {
        return mPreferences.getString(EMAIL_ID, null);
    }

    public void setEmail(String email) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(EMAIL_ID, email);
        editor.apply();
    }
}
