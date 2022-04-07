package http_test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/7 16:49
 */
public class Test1 {
    public static void main(String[] args) {
        /**
         * @see <a href="https://www.pdai.tech/md/java/java8up/java11.html">标准 HTTP Client 升级</a>
         */
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.baidu.com/"))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
