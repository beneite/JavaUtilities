# HttpClient

In **Java 11**, a brand-new **`HttpClient`** API was introduced in the `java.net.http` package — a modern, efficient, and fully asynchronous HTTP client that replaces the older `HttpURLConnection`.

Here’s a quick overview 👇

---

## 🧩 Import

```java
import java.net.http.*;
import java.net.URI;
```

---

## ✅ Simple GET Request Example

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleGetExample {
    public static void main(String[] args) throws Exception {
        // Create HttpClient (thread-safe, can be reused)
        HttpClient client = HttpClient.newHttpClient();

        // Create Request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();

        // Send Request (synchronous)
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print Response
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }
}
```

---

## ⚙️ POST Request Example (with JSON)

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class SimplePostExample {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String jsonBody = """
            {
              "title": "foo",
              "body": "bar",
              "userId": 1
            }
        """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }
}
```

---

## ⚡ Asynchronous Request Example

```java
import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;

public class AsyncGetExample {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .build();

        CompletableFuture<HttpResponse<String>> future =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // Non-blocking
        future.thenApply(HttpResponse::body)
              .thenAccept(System.out::println)
              .join();
    }
}
```

---

## 🛠️ Common Notes

| Feature              | Description                                             |
| -------------------- | ------------------------------------------------------- |
| **Package**          | `java.net.http`                                         |
| **HTTP/2 Support**   | Yes                                                     |
| **Asynchronous API** | Built-in via `CompletableFuture`                        |
| **Redirects**        | Controlled using `HttpClient.Builder.followRedirects()` |
| **Timeouts**         | Configurable per request                                |
| **Proxy / SSL**      | Supported via `HttpClient.Builder`                      |

---

### Example with Timeout and Redirects

```java
HttpClient client = HttpClient.newBuilder()
        .followRedirects(HttpClient.Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(10))
        .build();
```

---

Would you like me to show an example that **sends headers and reads JSON responses (parsed into a POJO)** using `HttpClient` + `Jackson`?
