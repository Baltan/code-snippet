package stock;

import lombok.experimental.Accessors;

import java.util.List;

/**
 * Description:
 *
 * @author baltan
 * @date 2025/5/12 17:08
 */
@lombok.Data
@Accessors(chain = true)
public class HongKongStockIntradayConditionResponse {
    private Integer rc;
    private Integer rt;
    private Long svr;
    private Integer lt;
    private Integer full;
    private String dlmkts;
    private Data data;

    @lombok.Data
    public static class Data {
        private Integer total;
        private List<Stock> diff;

        @lombok.Data
        public static class Stock {
            private Integer f1;
            /**
             * 最新价（港币（分））
             */
            private Integer f2;
            /**
             * 涨跌幅（%）
             */
            private Integer f3;
            /**
             * 涨跌额（港币（分））
             */
            private Integer f4;
            /**
             * 成交量（股）
             */
            private Long f5;
            /**
             * 成交额（港币（元））
             */
            private Double f6;
            /**
             * 股票代码
             */
            private String f12;
            private Integer f13;
            /**
             * 股票名称
             */
            private String f14;
            /**
             * 最高价（港币（分））
             */
            private Integer f15;
            /**
             * 最低价（港币（分））
             */
            private Integer f16;
            /**
             * 今日开盘价（港币（分））
             */
            private Integer f17;
            /**
             * 昨日收盘价（港币（分））
             */
            private Integer f18;
            private Integer f19;
            private Integer f152;
        }
    }
}