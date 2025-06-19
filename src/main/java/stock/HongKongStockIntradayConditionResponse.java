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
            private String f1; // Integer
            /**
             * 最新价（港币（分））
             */
            private String f2; // Integer
            /**
             * 涨跌幅（%）
             */
            private String f3; // Integer
            /**
             * 涨跌额（港币（分））
             */
            private String f4; // Integer
            /**
             * 成交量（股）
             */
            private String f5; // Long
            /**
             * 成交额（港币（元））
             */
            private String f6; // Double
            /**
             * 股票代码
             */
            private String f12;
            private String f13; // Integer
            /**
             * 股票名称
             */
            private String f14;
            /**
             * 最高价（港币（分））
             */
            private String f15; // Integer
            /**
             * 最低价（港币（分））
             */
            private String f16; // Integer
            /**
             * 今日开盘价（港币（分））
             */
            private String f17; // Integer
            /**
             * 昨日收盘价（港币（分））
             */
            private String f18; // Integer
            private String f19; // Integer
            private String f152; // Integer
        }
    }
}