package httpclient;

import com.alibaba.fastjson.JSON;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Description: 参考：<a href="https://www.cnblogs.com/bingyimeiling/p/11820583.html"></a>
 *
 * @author Baltan
 * @date 2019-12-06 16:47
 */
public class HttpUtil {
    private static final int MAX_TOTAL = 500;
    private static final int DEFAULT_MAX_PER_ROUTE = 100;
    private static final int MAX_PER_ROUTE = 200;
    private static final int CONNECT_RETRY_TIMES = 3;
    private static final int CONNECTION_REQUEST_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final int SOCKET_TIMEOUT = 5000;
    private static PoolingHttpClientConnectionManager manager;
    private static CloseableHttpClient httpClient;

    /**
     * 无法实例化工具类
     */
    private HttpUtil() {
    }

    public static Map<String, Object> doPost(String url, Map<String, Object> paramMap) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        setRequestConfig(httpPost);
        String resultString = "";
        CloseableHttpResponse response = null;

        try {
            httpPost.setEntity(new ByteArrayEntity(JSON.toJSONBytes(paramMap)));
            /**
             * 发起请求 并返回请求的响应
             */
            response = getHttpClient(url).execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            resultString = EntityUtils.toString(responseEntity, "utf-8");
            System.out.println(resultString);
            EntityUtils.consume(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.close();
        }
        return JSON.parseObject(resultString, Map.class);
    }

    private static CloseableHttpClient createHttpClient(String host, int port) throws UnknownHostException {
        ConnectionSocketFactory plainSocketFactory = PlainConnectionSocketFactory.getSocketFactory();
        LayeredConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactory.getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", plainSocketFactory).register("https", sslSocketFactory).build();
        manager = new PoolingHttpClientConnectionManager(registry);
        /**
         * 最大连接数
         */
        manager.setMaxTotal(MAX_TOTAL);
        /**
         * 路由最大连接数
         */
        manager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
        HttpHost httpHost = new HttpHost(host, port);
        manager.setMaxPerRoute(new HttpRoute(httpHost), MAX_PER_ROUTE);
        /**
         * 请求失败时,进行请求重试
         */
        HttpRequestRetryHandler handler = (e, retryTimes, httpContext) -> {
            if (retryTimes > CONNECT_RETRY_TIMES) {
                /**
                 * 重试超过3次，放弃请求
                 */
                e.printStackTrace();
                return false;
            }
            if (e instanceof NoHttpResponseException) {
                /**
                 * 服务器没有响应，可能是服务器断开了连接，应该重试
                 */
                e.printStackTrace();
                return true;
            }
            if (e instanceof SSLHandshakeException) {
                /**
                 * SSL握手异常
                 */
                e.printStackTrace();
                return false;
            }
            if (e instanceof InterruptedIOException) {
                /**
                 * 超时
                 */
                e.printStackTrace();
                return false;
            }
            if (e instanceof UnknownHostException) {
                /**
                 * 服务器不可达
                 */
                e.printStackTrace();
                return false;
            }
            if (e instanceof ConnectTimeoutException) {
                /**
                 * 连接超时
                 */
                e.printStackTrace();
                return false;
            }
            if (e instanceof SSLException) {
                /**
                 * SSL握手异常
                 */
                e.printStackTrace();
                return false;
            }

            HttpClientContext context = HttpClientContext.adapt(httpContext);
            HttpRequest request = context.getRequest();

            if (!(request instanceof HttpEntityEnclosingRequest)) {
                /**
                 * 如果请求不是关闭连接的请求，请求是幂等的，应该重试
                 */
                return true;
            }
            return false;
        };

        CloseableHttpClient client =
                HttpClients.custom().setConnectionManager(manager).setRetryHandler(handler)
                        .build();
        return client;
    }

    private static CloseableHttpClient getHttpClient(String url) throws UnknownHostException {
        String host = url.split("/")[2];
        int port = 80;

        if (host.contains(":")) {
            String[] args = host.split(":");
            host = args[0];
            port = Integer.parseInt(args[1]);
        }

        if (httpClient == null) {
            /**
             * 多线程下可能导致重复创建httpClient对象的问题
             */
            synchronized (HttpUtil.class) {
                if (httpClient == null) {
                    httpClient = createHttpClient(host, port);
                }
            }
        }
        return httpClient;
    }

    private static void setRequestConfig(HttpRequestBase httpRequestBase) {
        RequestConfig requestConfig =
                RequestConfig.custom()
                        .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                        .setConnectTimeout(CONNECT_TIMEOUT)
                        .setSocketTimeout(SOCKET_TIMEOUT).build();
        httpRequestBase.setConfig(requestConfig);
    }
}
