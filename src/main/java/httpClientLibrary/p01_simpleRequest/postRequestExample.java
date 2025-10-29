package httpClientLibrary.p01_simpleRequest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import httpClientLibrary.p01_simpleRequest.pojos.PostRequestDto;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class postRequestExample {

    HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static HttpRequest requestBuilder(String url, Map<String, String> requestHeader, String body){
        HttpRequest.Builder httpRequestbuilder = HttpRequest.newBuilder()
                .uri(URI.create(url));

        if(requestHeader != null){
            requestHeader.forEach((k,v) -> httpRequestbuilder.header(k,v));
        }

        httpRequestbuilder.POST(HttpRequest.BodyPublishers.ofString(body));

        return httpRequestbuilder.build();
    }

    @Test
    public void simplePostRequest() throws IOException, InterruptedException {
        Map<String, String> headers  = new HashMap<>();
        Faker faker = new Faker();
        PostRequestDto postRequestDto = PostRequestDto
                .builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();
        String url = "http://localhost:8080/api/user/create";
        headers.put("content-type","application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(postRequestDto);
        System.out.println("payload:"+payload);
        HttpRequest request = requestBuilder(url, headers, payload);

        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("status:"+httpResponse.statusCode());
        System.out.println("response body:"+httpResponse.body());
    }
}
