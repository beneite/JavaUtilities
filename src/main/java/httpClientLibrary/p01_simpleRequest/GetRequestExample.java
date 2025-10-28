package httpClientLibrary.p01_simpleRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import httpClientLibrary.p01_simpleRequest.pojos.GetAllUsersResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;


public class GetRequestExample {

    // Create reusable HTTP client
    HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    // Method to create GET request with optional headers
    public static HttpRequest createHttpRequest(String url, Map<String, String> headersMap){
        HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        if(headersMap != null){
            headersMap.forEach((k,v) -> httpRequestBuilder.header(k,v));
        }

        return httpRequestBuilder.build();
    }

    @Test
    public void simpleGetRequest() throws IOException, InterruptedException {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=35&longitude=139&current_weather=true";
        HttpRequest httpRequest = createHttpRequest(url, null);
        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response status:"+httpResponse.statusCode());
        System.out.println("Response:"+httpResponse.body());
    }

    @Test
    public void simpleGetRequestWithResponseParsing() throws IOException, InterruptedException {
        String url = "http://localhost:8080/api/user/getAllUser";
        HttpRequest httpRequest = createHttpRequest(url, null);
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        List<GetAllUsersResponseDto> users = objectMapper.readValue(httpResponse.body(), new TypeReference<List<GetAllUsersResponseDto>>() {});

        users.stream().forEach(e -> System.out.println(e.toString()));
    }

}
