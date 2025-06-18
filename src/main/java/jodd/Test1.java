package jodd;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * Description: http://www.cnblogs.com/davidwang456/p/4571327.html
 *
 * @author Baltan
 * @date 2018/11/6 09:31
 */
public class Test1 {
    public static void main(String[] args) {
        HttpRequest httpRequest = HttpRequest.get("https://www.hupu.com/");
        HttpResponse httpResponse = httpRequest.send();
        System.out.println(httpResponse);
    }
}
