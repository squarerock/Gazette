package squarerock.gazette.utils;

import android.content.Context;
import android.content.SharedPreferences;

import squarerock.gazette.Gazette;

/**
 * Created by pranavkonduru on 10/22/16.
 */

public class Preferences {

    public static final String PREF_KEY_NEWSDESK_ARTS = "arts";
    public static final String PREF_KEY_NEWSDESK_FASHION = "fashion";
    public static final String PREF_KEY_NEWSDESK_SPORTS = "sports";
    public static final String PREF_KEY_SORT_ORDER = "sort";
    public static final String PREF_KEY_START_DATE = "start_date";
    public static final String PREF_KEY_END_DATE = "end_date";

    public static final String PREFS = "prefs";

    public static void putBoolean(String key, boolean value){
        SharedPreferences pref = Gazette.getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public static boolean getBoolean(String key){
        SharedPreferences pref = Gazette.getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }

    public static void putString(String key, String value){
        SharedPreferences pref = Gazette.getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static String getString(String key){
        SharedPreferences pref = Gazette.getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

    public static void clearAll(Context context){
        SharedPreferences pref = Gazette.getContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.clear();
        edit.apply();
    }
}
