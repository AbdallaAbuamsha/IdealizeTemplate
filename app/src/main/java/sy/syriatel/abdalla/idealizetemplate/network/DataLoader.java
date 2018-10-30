package sy.syriatel.abdalla.idealizetemplate.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import sy.syriatel.abdalla.idealizetemplate.R;
import sy.syriatel.abdalla.idealizetemplate.helper.Utils;

/**
 * Created by Abdalla on 10/30/2018.
 */

/*
* Stander class in all project because the all api have same style “data, massage, code”
*/

public class DataLoader {

    private static final int NUM_RETRIES = 0;
    private static final int TIMEOUT_MS = 20000;

    public static void loadJsonDataPostWithProgress(Context context,
                                                    String url,
                                                    final OnJsonDataLoadedListener listener,
                                                    final Map<String, String> params,
                                                    final Request.Priority priority,
                                                    final String tag,
                                                    final ProgressDialog progress) {

        Utils.showProgress(progress);
        StringRequest JsonObjRequest = new StringRequest(Request.Method.POST, url, response -> {
            Utils.dismissProgress(progress);
            if (listener != null) {
                WebServiceResponse webServiceResponse = null;
                try {
                    webServiceResponse = JsonParser.json2WebServiceResponse(new JSONObject(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (webServiceResponse != null) {
                    if (webServiceResponse.getCode() < 0) {
                        try {
                            // TODO: DESCCUS THE ERROR TYPES
                            listener.onJsonDataLoadedWithError(webServiceResponse.getCode(), webServiceResponse.getErrorMessage());

                        } catch (Exception ignored) {
                            Log.e("error", ignored.getMessage());
                        }
                    } else if (webServiceResponse.getCode() > 0) {
                        listener.onJsonDataLoadedSuccessfully(webServiceResponse.getData());
                    }
                } else {
                    listener.onJsonDataLoadingFailure(R.string.error_parse);
                }
            }
        }, error -> {
            Utils.dismissProgress(progress);
            if (listener != null) {
                int errorId = R.string.error_connection;
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    errorId = R.string.error_connection;
                } else if (error instanceof AuthFailureError) {
                    errorId = R.string.error_connection;
                } else if (error instanceof ServerError) {
                    errorId = R.string.error_server;
                } else if (error instanceof NetworkError) {
                    errorId = R.string.error_connection;
                } else if (error instanceof ParseError) {
                    errorId = R.string.error_parse;
                }
                listener.onJsonDataLoadingFailure(errorId);
            }
        }) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("language", String.valueOf(Utils.getLanguage().ordinal()));
                return headers;
            }

        };
        JsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                NUM_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add request to request queue
        VolleySingleton.getInstance().addToRequestQueue(JsonObjRequest, tag);
        progress.setOnDismissListener(dialogInterface -> VolleySingleton.getInstance().cancelPendingRequests(tag));
    }

    public static void loadJsonDataPost(Context context, String url, final OnJsonDataLoadedListener listener,
                                        final Map<String, String> params, final Request.Priority priority, String tag) {

        StringRequest JsonObjRequest = new StringRequest(Request.Method.POST, url, response -> {
            if (listener != null) {
                WebServiceResponse webServiceResponse = null;
                try {
                    webServiceResponse = JsonParser.json2WebServiceResponse(new JSONObject(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (webServiceResponse != null) {
                    if (webServiceResponse.getCode() < 0) {
                        try {
                            // TODO: DESCCUS THE ERROR TYPES
                            listener.onJsonDataLoadedWithError(webServiceResponse.getCode(), webServiceResponse.getErrorMessage());

                        } catch (Exception ignored) {
                            Log.e("error", ignored.getMessage());
                        }
                    } else if (webServiceResponse.getCode() > 0) {
                        listener.onJsonDataLoadedSuccessfully(webServiceResponse.getData());
                    }
                } else {
                    listener.onJsonDataLoadingFailure(R.string.error_parse);
                }
            }
        },
                error -> {
                    if (listener != null) {
                        int errorId = R.string.error_connection;
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            errorId = R.string.error_connection;
                        } else if (error instanceof AuthFailureError) {
                            errorId = R.string.error_connection;
                        } else if (error instanceof ServerError) {
                            errorId = R.string.error_server;
                        } else if (error instanceof NetworkError) {
                            errorId = R.string.error_connection;
                        } else if (error instanceof ParseError) {
                            errorId = R.string.error_parse;
                        }
                        listener.onJsonDataLoadingFailure(errorId);
                    }
                }) {

            @Override
            public byte[] getBody() {
                String s = new JSONObject(params).toString();
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
//                headers.put("language", String.valueOf(Utils.getLanguage().ordinal()));
                return headers;
            }

        };
        JsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                NUM_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Add request to request queue
        VolleySingleton.addToRequestQueue(JsonObjRequest, tag);
    }
}
