/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.http;

import core.communication.CommunicationException;
import core.communication.ResponseCallback;
import core.communication.http.HttpClientInterface;
import core.communication.http.HttpConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author Manel
 */
public class HttpClient implements HttpClientInterface {

    @Deprecated
    @Override
    public void sendGetRequestAsync(ResponseCallback callback) throws CommunicationException {
        try {
            URL url = new URL("https://barcode.tec-it.com/barcode.ashx?data=ABC-abc-1234&code=Code128&translate-esc=on");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(HttpConstants.HttpRequestMethodType.GET.name());

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            callback.onResponse(content.toString(), con.getResponseCode());
        } catch (MalformedURLException | ProtocolException ex) {
            throw buildException(HttpConstants.HttpRequestMethodType.GET, ex);
        } catch (IOException ex) {
            throw buildException(HttpConstants.HttpRequestMethodType.GET, ex);
        }
    }

    private CommunicationException buildException(HttpConstants.HttpRequestMethodType methodType, Exception innerException) {
        return new CommunicationException("err", innerException);
    }
}
