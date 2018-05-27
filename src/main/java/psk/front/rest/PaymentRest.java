package psk.front.rest;

import org.primefaces.json.JSONObject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

@RequestScoped
@Named
public class PaymentRest {

    private String paymentProviderUrl = "https://mock-payment-processor.appspot.com/v1/payment";
    private String authUsername = "technologines";
    private String authPassword = "platformos";

    public JSONObject postProcessPayment(int amount, String cardNr, String holder, int expYear, int expMonth, String cvv) {
        JSONObject paymentJson = setupPaymentObject(amount, cardNr, holder, expYear, expMonth, cvv);

        try {
            HttpURLConnection conn = setupConnection();

            sendPostRequest(conn, paymentJson);

            JSONObject responseJson = getResponse(conn);

            conn.disconnect();

            return responseJson;
        }
        catch (Exception e) {
            e.printStackTrace();

            return createUnexpectedError();
        }
    }
    private HttpURLConnection setupConnection() throws IOException {
        URL url = new URL(paymentProviderUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        String basicAuth = "Basic " + Base64.getEncoder().encodeToString((authUsername + ":" + authPassword).getBytes());
        conn.setRequestProperty("Authorization",basicAuth);

        return conn;
    }

    private JSONObject setupPaymentObject(int amount, String cardNr, String holder, int expYear, int expMonth, String cvv) {
        JSONObject paymentJson = new JSONObject();

        paymentJson.put("amount", amount);
        paymentJson.put("number", cardNr);
        paymentJson.put("holder", holder);
        paymentJson.put("exp_year", expYear);
        paymentJson.put("exp_month", expMonth);
        paymentJson.put("cvv", cvv);

        return paymentJson;
    }

    private void sendPostRequest(HttpURLConnection conn, JSONObject paymentJson) throws IOException {
        OutputStream os = conn.getOutputStream();
        os.write(paymentJson.toString().getBytes("UTF-8"));
        os.close();
    }

    private JSONObject getResponse(HttpURLConnection conn) throws IOException {
        int status = conn.getResponseCode();

        if(status == HttpURLConnection.HTTP_CREATED) {
            InputStream inputStream = conn.getInputStream();
            JSONObject myResponse = new JSONObject(getStringFromInputStream(inputStream));
            inputStream.close();

            return myResponse;
        } else if(status == 402) {
            InputStream errorStream = conn.getErrorStream();
            JSONObject errorResponse = new JSONObject(getStringFromInputStream(errorStream));
            System.out.println(errorResponse.getString("error"));
            System.out.println(errorResponse.getString("message"));
            errorStream.close();

            return errorResponse;
        } else {
            return createUnexpectedError();
        }
    }

    private JSONObject createUnexpectedError() {
        JSONObject errorResponse = new JSONObject();

        errorResponse.put("error","UnexpectedError");
        errorResponse.put("message","An unexpected error has occured.");

        return errorResponse;
    }

    private String getStringFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString("UTF-8");
    }
}
