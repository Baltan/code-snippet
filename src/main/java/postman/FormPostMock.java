package postman;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/11/30 13:59
 */
public class FormPostMock {
    public static void main(String[] args) throws Exception {
        CustomerInfo info1 = new CustomerInfo("02491-007", "张三", "mock330101190001010000");
        System.out.println(generateFormParameter(info1));
        System.out.println("--------------------------------------------------------------");
        System.out.println(send(createParameter(generateAdditional(info1), info1)));
    }

    public static Additional generateAdditional(CustomerInfo info) {
        Additional additional = new Additional();
        String serviceCodeId = "10-" + info.getServiceCode();
        additional.setPowerMatters(serviceCodeId);
        additional.setSubPowerMatters(serviceCodeId);
        additional.setAccessCardId(info.getId());
        return additional;
    }

    public static Parameter generateParameter(Additional additional, CustomerInfo info) throws NoSuchAlgorithmException {
        String requestTime = String.valueOf(System.currentTimeMillis());
        String appKey = UUID.randomUUID().toString();
        String requestSecKey = UUID.randomUUID().toString();
        String deptId = UUID.randomUUID().toString();
        String areaCode = "330101";
        String applyCardType = "1";
        String busType = "1";
        String applyFrom = "1";
        String belongSystem = UUID.randomUUID().toString();

        String md5String = appKey + requestSecKey + requestTime;
        String sign = Md5Utils.md5(md5String);

        Parameter parameter = new Parameter();
        parameter.setDeptId(deptId);
        parameter.setAreaCode(areaCode);
        parameter.setServiceCodeId(additional.getPowerMatters());
        parameter.setApplyName(info.getName());
        parameter.setApplyCardType(applyCardType);
        parameter.setApplyCardNumber(info.getId());
        parameter.setBusType(busType);
        parameter.setApplyFrom(applyFrom);
        parameter.setBelongSystem(belongSystem);
        parameter.setAppKey(appKey);
        parameter.setSign(sign);
        parameter.setRequestTime(requestTime);
        parameter.setAdditional(additional);
        return parameter;
    }

    public static String generateFormParameter(CustomerInfo info) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Additional additional = generateAdditional(info);
        Parameter parameter = generateParameter(additional, info);
        String str = mapper.writeValueAsString(parameter).replaceAll("\"", "").replaceAll(",", "\n");
        int index = str.indexOf("additional:") + 11;
        return str.substring(1, index) + mapper.writeValueAsString(additional).replaceAll("\\s", "");
    }

    public static String createParameter(Additional additional, CustomerInfo info) throws Exception {
        String requestTime = String.valueOf(System.currentTimeMillis());
        String appKey = UUID.randomUUID().toString();
        String requestSecKey = UUID.randomUUID().toString();
        String deptId = UUID.randomUUID().toString();
        String areaCode = "330101";
        String applyCardType = "1";
        String busType = "1";
        String applyFrom = "1";
        String belongSystem = UUID.randomUUID().toString();

        String md5String = appKey + requestSecKey + requestTime;
        String sign = Md5Utils.md5(md5String);

        Map<String, Object> map = new HashMap<>();
        map.put("deptId", deptId);
        map.put("areaCode", areaCode);
        map.put("serviceCodeId", additional.getPowerMatters());
        map.put("applyName", info.getName());
        map.put("applyCardType", applyCardType);
        map.put("applyCardNumber", info.getId());
        map.put("busType", busType);
        map.put("applyFrom", applyFrom);
        map.put("belongSystem", belongSystem);
        map.put("appKey", appKey);
        map.put("sign", sign);
        map.put("requestTime", requestTime);
        map.put("additional", additional);

        StringBuilder sb = new StringBuilder();
        for (Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static String send(String parameter) throws Exception {
        URL url = new URL("https://www.test.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setUseCaches(false);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), StandardCharsets.UTF_8);
        osw.write(parameter);
        osw.flush();

        StringBuilder result = new StringBuilder();
        int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));

        if (contentLength > 0) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String temp;

            while ((temp = br.readLine()) != null) {
                result.append(temp);
            }
        }
        return result.toString();
    }
}
