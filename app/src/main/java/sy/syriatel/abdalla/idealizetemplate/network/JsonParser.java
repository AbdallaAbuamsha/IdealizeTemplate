package sy.syriatel.abdalla.idealizetemplate.network;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Abdalla on 10/30/2018.
 */

public class JsonParser {

    public static WebServiceResponse json2WebServiceResponse(JSONObject root) {
        int code = 0;
        try {
            code = root.getInt("code");
        } catch (JSONException e) {
        }

        String errorMessage = "";
        try {
            errorMessage = root.getString("errorMsg");
        } catch (JSONException e) {
        }

        JSONObject data = null;
        try {
            data = root.getJSONObject("data");
        } catch (JSONException ex) {
        }

        return new WebServiceResponse(code, errorMessage, data);

    }
}
