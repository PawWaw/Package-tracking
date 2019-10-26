package pl.polsl.api.allegro;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class allegroAPI {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    String url = "https://allegro.pl.allegrosandbox.pl/auth/oauth/token?grant_type=client_credentials";
    String clientID = "8dc210ae93144ac1adde3409c34ce54c";
    String clientSecret = "fcAGMlgRh9SBhMBmBrMaMgSCfczM5uSBKLdpxpPIORqphz1t3A6CRhgldl7CM83D";

    public String getUserToken(String code) throws Exception {

        HttpPost post = new HttpPost("https://allegro.pl.allegrosandbox.pl/auth/oauth/token?grant_type=authorization_code&code=" + code + "&redirect_uri=http://localhost:8080/allegro");

        HttpHeaders header = new HttpHeaders();

        String token = null;

        // add request headers
        String auth = clientID + ":" + clientSecret;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        post.setHeader(HttpHeaders.AUTHORIZATION, authHeader);


        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            String result = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = new JSONObject(result);
            System.out.println(jsonObject.getString("access_token"));
            token = jsonObject.getString("access_token");
            getBuys(token);
        }
        return token;
    }

    public String getPublicToken() throws Exception {

        HttpGet request = new HttpGet(url);
        HttpHeaders header = new HttpHeaders();

        // add request headers
        String auth = clientID + ":" + clientSecret;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            // return it as a String
            String result = EntityUtils.toString(entity);
            JSONObject jsonObject = new JSONObject(result);
            return jsonObject.getString("access_token");
        }
    }

    public Object getCategories(String token) throws Exception {
        String getCategoriesUrl = "https://api.allegro.pl.allegrosandbox.pl/sale/categories";

        HttpGet request = new HttpGet(getCategoriesUrl);
        HttpHeaders header = new HttpHeaders();

        String accept = "application/vnd.allegro.public.v1+json";
        request.addHeader(HttpHeaders.ACCEPT, accept);
        String authHeader = "Bearer " + token;
        request.addHeader(HttpHeaders.AUTHORIZATION, authHeader);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();

            String result = EntityUtils.toString(entity);
            JSONObject jsonObject = new JSONObject(result);
            return jsonObject;
        }
    }

    public Object getBuys(String token) throws Exception {

        JSONObject jsonObject = null;

        if(token != null)
        {
            String getCategoriesUrl = "https://api.allegro.pl.allegrosandbox.pl/me";

            HttpGet request = new HttpGet(getCategoriesUrl);
            HttpHeaders header = new HttpHeaders();

            String accept = "application/vnd.allegro.public.v1+json";
            request.addHeader(HttpHeaders.ACCEPT, accept);
            String authHeader = "Bearer " + token;
            request.addHeader(HttpHeaders.AUTHORIZATION, authHeader);

            try (CloseableHttpResponse response = httpClient.execute(request)) {

                // Get HttpResponse Status
                System.out.println(response.getStatusLine().toString());

                HttpEntity entity = response.getEntity();
                Header headers = entity.getContentType();

                String result = EntityUtils.toString(entity);
                jsonObject = new JSONObject(result);
                return jsonObject;
            }

        }

        return jsonObject;
    }


}
