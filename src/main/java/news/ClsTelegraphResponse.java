package news;

import com.alibaba.fastjson.annotation.JSONField;
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
public class ClsTelegraphResponse {
    private Integer error;
    private String msg;
    private Data data;

    @lombok.Data
    public static class Data {
        private List<RollDatum> rollData;

        @lombok.Data
        public static class RollDatum {
            private String level;
            private Integer readingNum;
            private String content;
            private String brief;
            private Long id;
            private Long ctime;
            private String title;
            private Long modifiedTime;
            private Integer status;
            private List<Stock> stockList;
            private List<Subject> subjects;

            @lombok.Data
            public static class Stock {
                private String name;
                @JSONField(name = "StockID")
                private String stockId;
                private String status;
                private Double last;
            }

            @lombok.Data
            public static class Subject {
                private String subjectName;
            }
        }
    }
}