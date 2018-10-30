package sy.syriatel.abdalla.idealizetemplate.network;

import org.json.JSONObject;

public interface OnJsonDataLoadedListener {
    public void onJsonDataLoadedSuccessfully(JSONObject data);

    public void onJsonDataLoadedWithError(int errorCode, String errorMessage);

    public void onJsonDataLoadingFailure(int errorId);
}
