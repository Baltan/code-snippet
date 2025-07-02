package message;

import cn.hutool.core.codec.Base64;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import lombok.SneakyThrows;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Description:
 *
 * @author baltan
 * @date 2025/5/13 16:11
 */
public class MessageUtil {
    private static final String ALGORITHM = "HmacSHA256";
    private static final String URL = "https://oapi.dingtalk.com/robot/send?timestamp=%d&sign=%s";
    private static final String ROBOT_SECRET = "SEC19449e34221e2faa1efcdbecebab4a747d396bc37e125b769bd7c677e3419e82";
    private static final String ROBOT_ACCESS_TOKEN = "1df6e1975231006405bef836084133691ef8c18711c8ea5688cad137e5747688";

    /**
     * 发送消息
     *
     * @param content
     * @param atAll
     * @param atUserIds
     * @param atMobiles
     */
    public static void sendMessage(String content, Boolean atAll, List<String> atUserIds, List<String> atMobiles) {
        Long timestamp = System.currentTimeMillis();
        String sign = getDingTalkMessageSign(timestamp);
        String url = String.format(URL, timestamp, sign);
        sendDingTalkMessage(content, url, atAll, atUserIds, atMobiles);
    }

    /**
     * 获取钉钉通知签名
     *
     * @param timestamp
     * @return
     */
    @SneakyThrows
    private static String getDingTalkMessageSign(Long timestamp) {
        String stringToSign = String.format("%d\n%s", timestamp, ROBOT_SECRET);
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec(ROBOT_SECRET.getBytes(StandardCharsets.UTF_8), ALGORITHM));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        return URLEncoder.encode(Base64.encode(signData), StandardCharsets.UTF_8.name());
    }

    /**
     * 发送钉钉通知
     *
     * @param content
     * @param url
     * @param atAll
     * @param atUserIds
     * @param atMobiles
     */
    @SneakyThrows
    private static void sendDingTalkMessage(String content, String url, Boolean atAll, List<String> atUserIds, List<String> atMobiles) {
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiRobotSendRequest req = new OapiRobotSendRequest();
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtUserIds(atUserIds);
        at.setAtMobiles(atMobiles);
        at.setIsAtAll(atAll);
        req.setMsgtype("text");
        req.setText(text);
        req.setAt(at);
        client.execute(req, ROBOT_ACCESS_TOKEN);
    }
}
