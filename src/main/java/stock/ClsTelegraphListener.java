package stock;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import message.MessageUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Description: 监听财联社24小时电报消息
 *
 * @author baltan
 * @date 2025/5/12 17:04
 */
public class ClsTelegraphListener {
    private static final String URL = "https://www.cls.cn/nodeapi/updateTelegraphList";
    private static final LocalTime END_TIME = LocalTime.of(16, 10, 0);
    private static final Set<String> SEND_MESSAGE_LEVELS = Sets.newHashSet("A", "B");
    private static final Long NEWS_EXPIRE_SECONDS = 30L;

    /**
     * 启动任务
     */
    public static void start() {
        while (true) {
            if (LocalTime.now().isAfter(END_TIME)) {
                break;
            }
            startListening();
            try {
                TimeUnit.SECONDS.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开始监听接口
     */
    private static void startListening() {
        try (HttpResponse response = HttpUtil.createGet(URL).execute()) {
            if (response.isOk()) {
                ClsTelegraphResponse clsTelegraphResponse = JSON.parseObject(response.body(), ClsTelegraphResponse.class);
                if (ObjectUtil.equals(clsTelegraphResponse.getError(), 0)) {
                    Optional.ofNullable(clsTelegraphResponse.getData().getRollData())
                            .orElse(Collections.emptyList())
                            .forEach(rollDatum -> {
                                if (SEND_MESSAGE_LEVELS.contains(rollDatum.getLevel())) {
                                    if (ObjectUtil.isNull(rollDatum.getCtime())) {
                                        return;
                                    }
                                    LocalDateTime newsTime = DateUtil.date(rollDatum.getCtime() * 1000).toLocalDateTime();
                                    if (LocalDateTimeUtil.between(newsTime, LocalDateTime.now(), ChronoUnit.SECONDS) > NEWS_EXPIRE_SECONDS) {
                                        return;
                                    }
                                    notice(rollDatum, newsTime);
                                }
                            });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 消息通知
     *
     * @param rollDatum
     * @param newsTime
     */
    private static void notice(ClsTelegraphResponse.Data.RollDatum rollDatum, LocalDateTime newsTime) {
        StringBuilder content = new StringBuilder(String.format("【消息级别】  %s\n【消息时间】  %s\n【消息内容】\n%s",
                Optional.ofNullable(rollDatum.getLevel()).orElse(""),
                LocalDateTimeUtil.formatNormal(newsTime),
                rollDatum.getContent()));
        if (CollUtil.isNotEmpty(rollDatum.getStockList())) {
            content.append("\n【关联股票】\n");
            for (ClsTelegraphResponse.Data.RollDatum.Stock stock : rollDatum.getStockList()) {
                content.append(String.format("%s （%s）\t", stock.getName(), stock.getStockId()));
            }
        }
        MessageUtil.sendMessage(content.toString(), ObjectUtil.equals(rollDatum.getLevel(), "A"));
    }
}
