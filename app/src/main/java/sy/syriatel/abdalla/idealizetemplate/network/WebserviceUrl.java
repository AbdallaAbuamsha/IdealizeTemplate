package sy.syriatel.abdalla.idealizetemplate.network;

/**
 * Created by Abdalla on 10/30/2018.
 */


/*
* define all api URL
*	Ex:  define TurnOnOffNotification api
*
 *note: getBaseUrl () return the server name

*/

public class WebserviceUrl {
    public static String BASE_URL = "";
    //only example:
    public static String getPath()
    {
        String URL = "";
        try {
            URL = getBaseUrl() + "some_path...";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return URL;
    }
    //ene of example
    public static String getBaseUrl() {
        return BASE_URL;
    }
}

