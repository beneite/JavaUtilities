package emailVerification;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    static HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {
        // Step 1: Get domain
        String domainResp = sendGet("https://api.mail.tm/domains");
        JSONArray domains = new JSONObject(domainResp).getJSONArray("hydra:member");
        String domain = domains.getJSONObject(0).getString("domain");

        // Step 2: Register random email
        String random = "";
        String email = "ashishmishra@ptct.net";
        String password = "Ashish@123";

        System.out.println("Using email: " + email);

        String registerPayload = String.format("{\"address\": \"%s\", \"password\": \"%s\"}", email, password);
        sendPost("https://api.mail.tm/accounts", registerPayload);

        // Step 3: Auth (get JWT)
        String tokenPayload = String.format("{\"address\": \"%s\", \"password\": \"%s\"}", email, password);
        String loginResp = sendPost("https://api.mail.tm/token", tokenPayload);
        String token = new JSONObject(loginResp).getString("token");

        // Step 4: Poll inbox
        String inboxUrl = "https://api.mail.tm/messages";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(inboxUrl))
                .header("Authorization", "Bearer " + token)
                .build();

        JSONObject message = null;
        for (int i = 0; i < 10; i++) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray messages = new JSONObject(response.body()).getJSONArray("hydra:member");

            if (!messages.isEmpty()) {
                message = messages.getJSONObject(0);
                break;
            }
            Thread.sleep(3000);
        }

        if (message == null) {
            System.out.println("No email received.");
            return;
        }

        // Step 5: Read message body
        String messageId = message.getString("id");
        HttpRequest msgRequest = HttpRequest.newBuilder()
                .uri(URI.create(inboxUrl + "/" + messageId))
                .header("Authorization", "Bearer " + token)
                .build();

        HttpResponse<String> msgResp = client.send(msgRequest, HttpResponse.BodyHandlers.ofString());
        JSONObject fullMsg = new JSONObject(msgResp.body());

        String body = fullMsg.getString("text");
        System.out.println("Email Body:\n" + body);

        // Optional: extract verification link
        Matcher m = Pattern.compile("(https?://\\S+)").matcher(body);
        if (m.find()) {
            System.out.println("Verification Link: " + m.group(1));
        }
    }

    static String sendGet(String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    static String sendPost(String url, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
