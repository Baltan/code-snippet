package alipay_login;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-28 16:13
 */
public class ScanLoginAuthorization {
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
    private String publicKey = "publicKey";
    /**
     * 回调地址
     */
    private String returnUrl = "http://127.0.0.1/alipayLogin/returnAuthCode";

    public static void main(String[] args) {
        ScanLoginAuthorization login = new ScanLoginAuthorization();

        String url = login.getAuthCode();
        System.out.println(url);

        String accessToken = login.getAccessToken("authCode");
        login.getUserInfo(accessToken);
    }

    public String getAuthCode() {
        String url = null;
        try {
            url =
                    "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=" + appId + "&scope=auth_user,auth_base&redirect_uri=" +
                            URLEncoder.encode(returnUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    public String getAccessToken(String authCode) {
        String accessToken = null;
        try {
            AlipayClient alipayClient =
                    new DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", "utf-8", publicKey, "RSA2");
            AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
            request.setCode(authCode);
            request.setGrantType("authorization_code");
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            accessToken = oauthTokenResponse.getAccessToken();

            System.out.println("oauthTokenResponse: " + oauthTokenResponse.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    public void getUserInfo(String accessToken) {
        try {
            AlipayClient alipayClient =
                    new DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", "utf-8", publicKey, "RSA2");
            AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
            AlipayUserInfoShareResponse userinfoShareResponse = alipayClient.execute(request, accessToken);

            System.out.println("userinfoShareResponse: " + userinfoShareResponse.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
