package alipay_login;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayUserCertifyOpenCertifyRequest;
import com.alipay.api.request.AlipayUserCertifyOpenInitializeRequest;
import com.alipay.api.request.AlipayUserCertifyOpenQueryRequest;
import com.alipay.api.response.AlipayUserCertifyOpenCertifyResponse;
import com.alipay.api.response.AlipayUserCertifyOpenInitializeResponse;
import com.alipay.api.response.AlipayUserCertifyOpenQueryResponse;

import java.util.UUID;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-15 16:02
 */
public class ScanLogin {
    /**
     * 芝麻开放平台地址
     */
    private String gatewayUrl = "https://openapi.alipay.com/gateway.do";
    /**
     * 商户应用 Id
     */
    private String appId = "appId";
    /**
     * 商户 RSA 私钥
     */
    private String privateKey = "privateKey";
    /**
     * 芝麻 RSA 公钥
     */
    private String publicKey = "zhimaPublicKey";

    public static void main(String[] args) {
        ScanLogin login = new ScanLogin();

        String certifyId = login.customerCertificationInitialize();
        login.customerCertificationCertify(certifyId);

        login.userCertifyOpenQuery(certifyId);
    }

    public String customerCertificationInitialize() {
        String certifyId = null;

        try {
            AlipayClient alipayClient =
                    new DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", "utf-8", publicKey, "RSA2");
            AlipayUserCertifyOpenInitializeRequest request = new AlipayUserCertifyOpenInitializeRequest();
            /**
             * 构造身份信息json对象
             */
            JSONObject identityObj = new JSONObject();
            /**
             * 身份类型，必填
             */
            identityObj.put("identity_type", "ALIPAY_CERT_INFO");
            /**
             * 证件类型，必填
             */
            identityObj.put("cert_type", "IDENTITY_CARD");
            /**
             * 证件类型，必填
             */
            identityObj.put("cert_name", "张三");
            /**
             * 证件号码，必填
             */
            identityObj.put("cert_no", "mock330101190001010000");
            /**
             * 构造商户配置json对象
             */
            JSONObject merchantConfigObj = new JSONObject();
            /**
             * 设置回调地址,必填
             */
            merchantConfigObj.put("return_url", "http://127.0.0.1/login/getUserInfo");
            /**
             * 构造身份认证初始化服务业务参数数据
             */
            JSONObject bizContentObj = new JSONObject();
            /**
             * 商户请求的唯一标识，推荐为uuid，必填
             */
            bizContentObj.put("outer_order_no", UUID.randomUUID().toString().replace("-", ""));
            bizContentObj.put("biz_code", "FACE");
            bizContentObj.put("identity_param", identityObj);
            bizContentObj.put("merchant_config", merchantConfigObj);
            request.setBizContent(bizContentObj.toString());
            /**
             * 发起请求
             */
            AlipayUserCertifyOpenInitializeResponse response = alipayClient.execute(request);

            if (response.isSuccess()) {
                System.out.println("调用成功");
                /**
                 * 接口调用成功，从返回对象中获取certify_id
                 */
                certifyId = response.getCertifyId();
                /**
                 * 执行后续流程……
                 */
                System.out.println("certifyId： " + certifyId);
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return certifyId;
    }

    public void customerCertificationCertify(String certifyId) {
        try {
            /**
             * 获取alipay client
             */
            AlipayClient alipayClient =
                    new DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", "utf-8", publicKey, "RSA2");
            AlipayUserCertifyOpenCertifyRequest request = new AlipayUserCertifyOpenCertifyRequest();

            /**
             * 设置certifyId
             */
            JSONObject bizContentObj = new JSONObject();
            bizContentObj.put("certify_id", certifyId);
            request.setBizContent(bizContentObj.toString());

            /**
             * 生成请求链接，这里一定要使用GET模式
             */
            AlipayUserCertifyOpenCertifyResponse response = alipayClient.pageExecute(request, "GET");
            if (response.isSuccess()) {
                System.out.println("开始认证服务调用成功");
                String certifyUrl = response.getBody();
                /**
                 * 执行后续流程……
                 */
                System.out.println("certifyUrl： " + certifyUrl);
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void userCertifyOpenQuery(String certifyId) {
        try {
            AlipayClient alipayClient =
                    new DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", "utf-8", publicKey, "RSA2");
            AlipayUserCertifyOpenQueryRequest request = new AlipayUserCertifyOpenQueryRequest();
            request.setBizContent("{\"certify_id\":\"" + certifyId + "\"}");
            AlipayUserCertifyOpenQueryResponse response = alipayClient.execute(request);

            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
