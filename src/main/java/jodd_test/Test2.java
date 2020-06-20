package jodd_test;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * Description: http://www.cnblogs.com/davidwang456/p/4571327.html
 *
 * @author Baltan
 * @date 2018/11/6 09:34
 */
public class Test2 {
    public static void main(String[] args) {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest
                .method("GET")
                .protocol("https")
                .host("www.hupu.com");
//                .port()
//                .path("");
        HttpResponse httpResponse = httpRequest.send();
        System.out.println(httpResponse);
    }
}
