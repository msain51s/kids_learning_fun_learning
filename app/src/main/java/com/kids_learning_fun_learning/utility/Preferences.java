package com.kids_learning_fun_learning.utility;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Administrator on 11/10/2017.
 */

public class Preferences {

    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;
    static Preferences prefs;
    int PRIVATE_MODE = 0;

    public static final String PREF_NAME = "kid_learning_fun_learning";

    private static final String APP_UPGRADE="app_upgrade_time";

    public Preferences(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setAppUpgradeTime(String value) {
        editor.putString(APP_UPGRADE, value);
        editor.commit();
    }

    public String getAppUpgradeTime() {
        return pref.getString(APP_UPGRADE, null);

    }
}
