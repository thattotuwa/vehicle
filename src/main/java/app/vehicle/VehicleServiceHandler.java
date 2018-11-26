package app.vehicle;


import com.netflix.config.DynamicPropertyFactory;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import static app.vehicle.VehicleConstants.*;


public class VehicleServiceHandler {
    private static String requestUrl = DynamicPropertyFactory.getInstance().getStringProperty("datapak.api.directUrl", "").getValue();
    private static HttpClient httpClient = new HttpClient();

    public static JSONObject getVehicle(String subscriptionAssignment,
                                        String modelId,
                                        String market,
                                        String locale,
                                        String username) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_MODELID, modelId);

        return getResponseObject(subscriptionAssignment, market, locale, username, MODEL_GET_PATH, params);
    }


    public static JSONObject search(String subscriptionAssignment,
                                    String term ,
                                    String type,
                                    String market,
                                    String locale,
                                    String username) throws Exception {


        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_SEARCH_TYPE, type);
        params.put(PARAM_SEARCH_TERM, term);

        return getResponseObject(subscriptionAssignment, market, locale, username, MODEL_SEARCH_PATH, params);

    }


    public static JSONObject list(String subscriptionAssignment,
            String familyCode,
            String market,
            String locale,
            String username) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_FAMILY_CODE, familyCode);

        return getResponseObject(subscriptionAssignment, market, locale, username, MODEL_LIST_PATH, params);
    }

    public static JSONObject listFamilies(String subscriptionAssignment,
                                          String market,
                                          String locale,
                                          String username) throws Exception {

        return getResponseObject(subscriptionAssignment, market, locale, username, MODEL_LIST_FAMILY_PATH, null);
    }



    public static JSONObject listSeries(String subscriptionAssignment,
                                        String familyCode,
                                        String market,
                                        String locale,
                                        String username) throws Exception {

        HashMap<String, String> params = new HashMap<>();
        params.put(PARAM_FAMILY_CODE, familyCode);

        return getResponseObject(subscriptionAssignment, market, locale, username, MODEL_LIST_SERIES_PATH, params);
    }

    private static Request getContentResponse(String subscriptionAssignment, String market, String locale, String URI, String username) throws InterruptedException, TimeoutException, ExecutionException {

        return httpClient.newRequest(requestUrl)
                .path(DATAPAK_SERVICE_PATH + URI)
                .param(PARAM_CSID,subscriptionAssignment)
                .param(PARAM_MARKET, market)
                .param(PARAM_LOCALE, locale)
                .method(HttpMethod.GET)
                .header("Accept", HEADER_ACCEPT_VALUE)
                .header("Content-Type", HEADER_ACCEPT_VALUE)
                .header(PARAM_IFM_UID,username);
    }



    private static JSONObject getResponseObject(String subscriptionAssignment, String market, String locale, String username, String uri, HashMap<String, String> params) throws Exception {
        httpClient.setFollowRedirects(false);
        // Start HttpClient
        httpClient.start();

        Request request = getContentResponse(subscriptionAssignment, market, locale, uri, username);
        if (params != null) {
              params.entrySet().forEach(entry -> request.param(entry.getKey(), entry.getValue()));
        }

        ContentResponse response = request
                .send();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.getContentAsString());

        httpClient.stop();
        return json;
    }

}
