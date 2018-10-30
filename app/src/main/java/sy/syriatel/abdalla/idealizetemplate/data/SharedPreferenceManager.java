package sy.syriatel.abdalla.idealizetemplate.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Abdalla on 10/30/2018.
 */


/*
* 1- We save constant values at the entire project level
*    ex: user name, user gender, app language ….value we need in all project
*        Define the values in the following way:
*        public static final String KEY_NAME=projectName.syriatel.sy.KEY_NAME";
*
* 2- We have two main functions : saveToPreferances / readFormPreferances
*
*/
public class SharedPreferenceManager {

    public static final String DEFAULT_FILE_NAME = "mainFile";

    public static void saveToPreferences(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String key, String defaultValue) {
        if (context != null) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            return sharedPreferences.getString(key, defaultValue);
        } else {
            return "";
        }
    }

    public static void clearPreferences(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        prefs.edit().clear().apply();
    }
}
