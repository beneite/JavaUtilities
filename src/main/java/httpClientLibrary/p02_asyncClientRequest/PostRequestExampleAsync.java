package httpClientLibrary.p02_asyncClientRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import httpClientLibrary.p01_syncRequest.pojos.PostRequestDto;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class PostRequestExampleAsync {

    HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static HttpRequest requestBuilder(String url, Map<String, String> requestHeader, String body) {
        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url));

        if (requestHeader != null) {
            requestHeader.forEach(httpRequestBuilder::header);
        }

        httpRequestBuilder.POST(HttpRequest.BodyPublishers.ofString(body));

        return httpRequestBuilder.build();
    }

    @Test
    public void simplePostRequestAsync() throws IOException, InterruptedException, ExecutionException {
        Map<String, String> headers = new HashMap<>();
        Faker faker = new Faker();

        // Example DTO — you can replace it with your own
        PostRequestDto postRequestDto = PostRequestDto.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        String url = "http://localhost:8080/api/user/create";
        headers.put("Content-Type", "application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(postRequestDto);
        System.out.println("payload: " + payload);

        HttpRequest request = requestBuilder(url, headers, payload);

        // 🔹 Send asynchronously (non-blocking)
        CompletableFuture<HttpResponse<String>> futureResponse =
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // 🔹 Handle response when it arrives
        futureResponse.thenAccept(response -> {
            System.out.println("Status code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        });

        // 🔹 Optionally, block until all async operations finish (for demo/test)
        futureResponse.join();

        System.out.println("Main thread continues immediately after sendAsync()");
    }

}
