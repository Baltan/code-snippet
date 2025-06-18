package crawler_test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Description: HTTP工具类
 *
 * @author Baltan
 * @date 2020-06-24 08:56
 */
public class HttpUtil {
    public static void main(String[] args) throws Exception {
        String url1 = "http://localhost:80/getCustomerInfoByCustomerNumber";
        Map<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("customerNumber", "891003167014");
        String responseContent1 = httpGet(url1, paramMap1);
        JSONObject content1 = parseResponseContent(responseContent1, JSONObject.class, "data");
        System.out.println(content1);

        System.out.println("\r\r");

        String url2 = "http://localhost:80/loan/riskAssessment";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("idNumber", "442824196711169538");
        paramMap2.put("name", "汤两觅");
        paramMap2.put("customerNumber", "891003167014");
        String responseContent2 = httpPostWithJSONParameters(url2, paramMap2);
        JSONObject content2 = parseResponseContent(responseContent2, JSONObject.class, "data");
        System.out.println(content2);

        System.out.println("\r\r");

        String url3 = "http://localhost:80/getCustomerInfoByIdNumber";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("idNumber", "442824196711169538");
        String responseContent3 = httpPostWithFormDataParameters(url3, paramMap3);
        JSONObject content3 = parseResponseContent(responseContent3, JSONObject.class, "data");
        System.out.println(content3);
    }

    /**
     * 发送GET请求
     *
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    private static String httpGet(String url, Map<String, Object> paramMap) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        String queryStringParameter = buildQueryStringParameter(paramMap);
        HttpGet httpGet = new HttpGet(url + queryStringParameter);
        addHttpHeaders(httpGet);
        HttpResponse httpResponse = client.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
        return responseContent;
    }

    /**
     * 发送POST请求，请求参数为Form Date类型
     *
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    private static String httpPostWithFormDataParameters(String url, Map<String, Object> paramMap)
            throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        UrlEncodedFormEntity urlEncodedFormEntity = buildFormDataParameter(paramMap);
        HttpPost httpPost = new HttpPost(url);
        addHttpHeaders(httpPost);
        httpPost.setEntity(urlEncodedFormEntity);
        HttpResponse httpResponse = client.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
        return responseContent;
    }

    /**
     * 发送POST请求，请求参数为JSON类型
     *
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    private static String httpPostWithJSONParameters(String url, Map<String, Object> paramMap)
            throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        StringEntity stringEntity = buildJSONParameter(paramMap);
        HttpPost httpPost = new HttpPost(url);
        addHttpHeaders(httpPost);
        httpPost.setEntity(stringEntity);
        HttpResponse httpResponse = client.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String responseContent = EntityUtils.toString(httpEntity, "UTF-8");
        return responseContent;
    }

    /**
     * 为HTTP请求添加请求头参数
     *
     * @param httpRequestBase
     */
    private static void addHttpHeaders(HttpRequestBase httpRequestBase) {
        if (httpRequestBase instanceof HttpGet) {
            HttpGet httpGet = (HttpGet) httpRequestBase;
            httpGet.setHeader("x-token", "");
        } else if (httpRequestBase instanceof HttpRequestBase) {
            HttpPost httpPost = (HttpPost) httpRequestBase;
            httpPost.setHeader("x-token", "");
        }
    }

    /**
     * 组装JSON类型的请求参数
     *
     * @param paramMap
     * @return
     */
    private static StringEntity buildJSONParameter(Map<String, Object> paramMap) {
        JSONObject paramObj = new JSONObject();

        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            paramObj.put(entry.getKey(), entry.getValue());
        }

        StringEntity stringEntity = new StringEntity(paramObj.toString(), "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        return stringEntity;
    }

    /**
     * 组装Query String Parameters类型的请求参数
     *
     * @param paramMap
     * @return
     */
    private static String buildQueryStringParameter(Map<String, Object> paramMap) {
        StringBuilder builder = new StringBuilder("?");

        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            builder.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    /**
     * 组装Form Date类型的参数
     *
     * <code>
     * httpPost.setEntity(urlEncodedFormEntity);
     * </code>
     *
     * @param paramMap
     * @return
     */
    private static UrlEncodedFormEntity buildFormDataParameter(Map<String, Object> paramMap)
            throws UnsupportedEncodingException {
        List<BasicNameValuePair> paramList = new ArrayList<>();

        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            paramList.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        return new UrlEncodedFormEntity(paramList, "utf-8");
    }

    /**
     * 解析响应报文
     *
     * @param responseContent
     * @param <T>
     * @return
     */
    private static <T> T parseResponseContent(String responseContent, Class clazz, String key) {
        JSONObject responseObj = JSONObject.parseObject(responseContent);

        if (Objects.equals(clazz.getSimpleName(), "JSONObject") &&
                responseObj.get(key) instanceof JSONObject) {
            return (T) responseObj.getJSONObject(key);
        } else if (Objects.equals(clazz.getSimpleName(), "JSONArray") &&
                responseObj.get(key) instanceof JSONArray) {
            return (T) responseObj.getJSONArray(key);
        }
        return (T) responseObj.getString(key);
    }
}
