package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPRequest {

    public HTTPResponse getHTMLCode(String urlToRead) throws Exception {
        HTTPResponse response = new HTTPResponse();
        String httpsURL = urlToRead;
        URL myUrl = new URL(httpsURL);
        HttpURLConnection conn = (HttpURLConnection)myUrl.openConnection();
        response.setResponseCode(conn.getResponseCode());

        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer responsebody = new StringBuffer();
        String inputLine = "";

        while ((inputLine = br.readLine()) != null) {
            responsebody.append(inputLine);
        }

        br.close();

        response.setResponseBody(responsebody.toString());

        return response;
    }
}
