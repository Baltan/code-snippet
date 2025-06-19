package stock;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import message.MessageUtil;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Description: 监听港股通股票当日行情
 *
 * @author baltan
 * @date 2025/6/18 15:32
 * @see <a href="https://quote.eastmoney.com/center/gridlist.html#hk_sh_stocks"></a>
 */
public class HongKongStockIntradayConditionListener {
    private static final String URL = "https://push2.eastmoney.com/api/qt/clist/get?np=1&fltt=1&invt=2&cb=jQuery37108670217273211989_1750231509621&fs=b:MK0144&fields=f12,f13,f14,f19,f1,f2,f4,f3,f152,f17,f18,f15,f16,f5,f6&fid=f3&pn=1&pz=5000&po=1&dect=1&ut=fa5fd1943c7b386f172d6893dbfba10b&wbp2u=6117356146666876|0|1|0|web&_=1750231509634";
    private static final LocalTime END_TIME = LocalTime.of(16, 10, 0);
    private static final Integer CONSECUTIVE_RISING_STOCK_DEQUE_SIZE = 7;
    private static final Integer RISING_TIMES_THRESHOLD = 5;
    private static final ConcurrentHashMap<String, Deque<HongKongStockIntradayConditionResponse.Data.Stock>> CONSECUTIVE_RISING_STOCK_MAP = new ConcurrentHashMap<>();

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
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 开始监听接口
     */
    private static void startListening() {
        try (HttpResponse httpResponse = HttpUtil.createGet(URL).execute()) {
            if (httpResponse.isOk()) {
                int start = httpResponse.body().indexOf("(") + 1;
                int end = httpResponse.body().lastIndexOf(")");
                HongKongStockIntradayConditionResponse response = JSON.parseObject(httpResponse.body().substring(start, end), HongKongStockIntradayConditionResponse.class);
                List<HongKongStockIntradayConditionResponse.Data.Stock> stocks = response.getData().getDiff();
                consecutiveRisingNotice(stocks);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连续{@link CONSECUTIVE_RISING_STOCK_DEQUE_SIZE}次请求得到实时股价非递减，且有上涨趋势
     *
     * @param stocks
     */
    private static void consecutiveRisingNotice(List<HongKongStockIntradayConditionResponse.Data.Stock> stocks) {
        stocks.parallelStream()
                .forEach(stock -> {
                    Deque<HongKongStockIntradayConditionResponse.Data.Stock> stockDeque =
                            CONSECUTIVE_RISING_STOCK_MAP.computeIfAbsent(stock.getF12(), i -> new ArrayDeque<>(CONSECUTIVE_RISING_STOCK_DEQUE_SIZE));
                    stockDeque.offerLast(stock);
                    while (stockDeque.size() > CONSECUTIVE_RISING_STOCK_DEQUE_SIZE) {
                        stockDeque.pollFirst();
                    }
                    if (stockDeque.size() < CONSECUTIVE_RISING_STOCK_DEQUE_SIZE) {
                        return;
                    }
                    List<Double> prices = stockDeque.stream()
                            .filter(x -> StrUtil.isNotBlank(x.getF2()) && ObjectUtil.notEqual(x.getF2(), "-"))
                            .map(x -> Double.parseDouble(x.getF2()) / 100)
                            .collect(Collectors.toList());
                    int risingTimes = 0;
                    for (int i = 1; i < prices.size(); i++) {
                        if (prices.get(i - 1) > prices.get(i)) {
                            return;
                        } else if (prices.get(i - 1) < prices.get(i)) {
                            risingTimes++;
                        }
                    }
                    if (risingTimes >= RISING_TIMES_THRESHOLD) {
                        String content = String.format("%s（%s）股价持续拉升，近%d次获取到股价为%s元，实时成交额为%s", stock.getF14(), stock.getF12(), CONSECUTIVE_RISING_STOCK_DEQUE_SIZE, prices, StrUtil.isNotBlank(stock.getF6()) && ObjectUtil.notEqual(stock.getF6(), "-") ? getAmount(Double.parseDouble(stock.getF6()), Boolean.FALSE) : "-");
                        MessageUtil.sendMessage(content, Boolean.TRUE);
                    }
                });
    }

    /**
     * 获取金额
     *
     * @param value    接口响应金额
     * @param centUnit 接口响应金额单位是否为【分】
     * @return
     */
    private static String getAmount(double value, boolean centUnit) {
        if (value >= 100_000_000) {
            return value / 100_000_000 / (centUnit ? 100 : 1) + "亿元";
        } else if (value >= 10_000) {
            return value / 10_000 / (centUnit ? 100 : 1) + "万元";
        } else {
            return value / (centUnit ? 100 : 1) + "元";
        }
    }
}
