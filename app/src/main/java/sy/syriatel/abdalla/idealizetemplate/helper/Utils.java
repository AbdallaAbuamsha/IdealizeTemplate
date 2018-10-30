package sy.syriatel.abdalla.idealizetemplate.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.Toast;

import sy.syriatel.abdalla.idealizetemplate.R;

/**
 * Created by Abdalla on 10/30/2018.
 */

/*
* In this class we define functions we need to use in all project
* Ex: we need date in same format in all project so we will define function to change date format in this class
*/

public class Utils {

    public static float convertDpToPixel(Context context, float dp) {
        DisplayMetrics mMetrics = context.getResources().getDisplayMetrics();
        return dp * mMetrics.density;
    }

    public static float convertPixelsToDp(Context context, float px) {
        DisplayMetrics mMetrics = context.getResources().getDisplayMetrics();
        return px / mMetrics.density;
    }

    public static void makeToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }


    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    public static void showProgress(ProgressDialog dialog) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.setCancelable(false);
            dialog.setMessage(ELibraryApplication.getInstance()
                    .getString(R.string.progress_dialog_loading));
            try {
                dialog.show();
            } catch (Exception e) {
            }
        }
    }

    public static void dismissProgress(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }

        }
    }

}
