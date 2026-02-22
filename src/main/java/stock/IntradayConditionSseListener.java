package stock;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONPath;
import lombok.SneakyThrows;
import system.Test9;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.InboundSseEvent;
import javax.ws.rs.sse.SseEventSource;
import java.util.concurrent.TimeUnit;

/**
 * Description: SSE事件持续监听【上海复旦（HK1385）】股价
 *
 * @author baltan
 * @date 2026/2/13 10:50
 */
public class IntradayConditionSseListener {
    @SneakyThrows
    public static void main(String[] args) {
        String url = "https://59.push2.eastmoney.com/api/qt/stock/sse?fields=f58,f107,f57,f43,f59,f169,f170,f152,f46,f60,f44,f45,f47,f48,f19,f39,f161,f49,f171,f50,f86,f600,f601,f154,f84,f85,f168,f108,f116,f167,f164,f92,f71,f117,f177,f123,f124,f125,f174,f175,f126,f257,f256,f258,f251,f255,f252,f254,f253,f198,f40,f37,f38,f35,f36,f33,f34,f31,f32,f20,f17,f18,f15,f16,f13,f14,f11,f12,f530,f292,f301,f752,f751&mpi=1000&invt=2&fltt=1&secid=116.01385&ut=fa5fd1943c7b386f172d6893dbfba10b&dect=1&wbp2u=6117356146666876";
        /**
         * 创建JAX-RS客户端
         */
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        /**
         * 构建SSE事件源
         */
        SseEventSource eventSource = SseEventSource.target(target)
                /**
                 * 设置重连间隔，默认500毫秒
                 */
                .reconnectingEvery(3, TimeUnit.SECONDS)
                .build();
        /**
         * 注册事件处理器
         */
        eventSource.register((InboundSseEvent event) ->
                        /**
                         * 每收到一条消息触发
                         */
                {
                    String data = event.readData(String.class);
                    String eventId = event.getId();
                    String eventName = event.getName();
                    System.out.println("收到事件 [id=" + eventId + ", name=" + eventName + "]: " + data);
                    if (ObjectUtil.isNotNull(JSONPath.read(data, "$.data.f43"))) {
                        double price = Double.parseDouble(String.valueOf(JSONPath.read(data, "$.data.f43"))) / 1000;
                        Test9.speak("上海复旦当前股价为" + price + "元");
                    }
                },
                /**
                 * 连接异常或协议错误触发
                 */
                Throwable::printStackTrace,
                /**
                 * 流正常结束（服务端主动关闭）触发
                 */
                () -> System.out.println("SSE流已正常结束")
        );
        /**
         * 打开连接，开始接收消息
         */
        eventSource.open();
        /**
         * 保持主线程运行（SSE是异步接收的）
         */
        System.out.println("SSE客户端已启动，等待事件...");
//            /**
//             * 关闭资源
//             */
//            eventSource.close();
//            client.close();
    }
}
