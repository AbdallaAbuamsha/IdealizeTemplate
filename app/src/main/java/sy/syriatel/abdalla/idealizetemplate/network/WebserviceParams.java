package sy.syriatel.abdalla.idealizetemplate.network;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abdalla on 10/30/2018.
 */

public class WebserviceParams {

    public static Map<String, String> getHeaders() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
