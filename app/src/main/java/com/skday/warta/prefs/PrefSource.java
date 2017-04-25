package com.skday.warta.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by skday on 4/25/17.
 */

public class PrefSource {

    private static final String NEWS_SOURCE = "NEWS_SOURCE";
    private static final String SOURCE_VALUE = "SOURCE_VALUE";

    private PrefSource() {}

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(NEWS_SOURCE, Context.MODE_PRIVATE);
    }

    public static String getSource(Context context) {
        return getSharedPreferences(context).getString(SOURCE_VALUE, null);
    }

    public static void setSource(Context context, String source) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(SOURCE_VALUE , source);
        editor.commit();
    }
}
