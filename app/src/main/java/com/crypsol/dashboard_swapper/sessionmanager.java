package com.crypsol.dashboard_swapper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class sessionmanager {

    private static String TAG = sessionmanager.class.getSimpleName();
    static SharedPreferences pref;
    static SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String KEY_LAYOUTVIEW = "layoutview";


    public sessionmanager(Context context) {
        this._context = context;
        pref = this._context.getSharedPreferences("this.Session", this.PRIVATE_MODE);
        editor = pref.edit();
    }

    public static void setLayoutView(String layout) {
        editor.putString(KEY_LAYOUTVIEW, layout);
        editor.commit();
    }

    public static String getLayoutView() {
        return pref.getString(KEY_LAYOUTVIEW, "");
    }

}
