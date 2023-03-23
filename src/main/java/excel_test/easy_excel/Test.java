package excel_test.easy_excel;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Baltan
 * @date 2023/3/23 16:59
 */
public class Test {
    public static void main(String[] args) {
        /**
         * 动态更新导出模板，增加count列
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        /**
         * 导出数据
         */
        List<Billing> data = Arrays.asList(
                new Billing("aaa", Arrays.asList(
                        new Adjustment(LocalDate.of(2022, Month.JANUARY, 1), 5, 4, -1),
                        new Adjustment(LocalDate.of(2022, Month.FEBRUARY, 1), 5, 7, 2),
                        new Adjustment(LocalDate.of(2022, Month.MARCH, 1), 4, 9, 5)
                )),
                new Billing("bbb", Arrays.asList(
                        new Adjustment(LocalDate.of(2022, Month.JANUARY, 1), 6, 8, 2),
                        new Adjustment(LocalDate.of(2022, Month.MARCH, 1), 5, 3, -2),
                        new Adjustment(LocalDate.of(2022, Month.APRIL, 1), 8, 9, 1),
                        new Adjustment(LocalDate.of(2022, Month.FEBRUARY, 1), 7, 7, 0)
                )),
                new Billing("ccc", Arrays.asList(
                        new Adjustment(LocalDate.of(2022, Month.SEPTEMBER, 1), 6, 8, 2),
                        new Adjustment(LocalDate.of(2022, Month.OCTOBER, 1), 5, 3, -2),
                        new Adjustment(LocalDate.of(2023, Month.JANUARY, 1), 10, 13, 3),
                        new Adjustment(LocalDate.of(2022, Month.DECEMBER, 1), 7, 3, -4),
                        new Adjustment(LocalDate.of(2023, Month.FEBRUARY, 1), 10, 3, -7),
                        new Adjustment(LocalDate.of(2022, Month.NOVEMBER, 1), 6, 7, 1)
                ))
        );
        /**
         * 所有实例中账期最早时间
         */
        LocalDate earliest = LocalDate.MAX;
        /**
         * 所有实例中账期最晚时间
         */
        LocalDate latest = LocalDate.MIN;
        /**
         * 要增加的表头
         */
        List<LocalDate> billTimes = new ArrayList<>();
        /**
         * 将每个实例的调账记录按照账期升序排列
         */
        for (int i = 0; i < data.size(); i++) {
            Billing x = data.get(i);
            Collections.sort(x.adjustmentList, Comparator.comparing(a -> a.billTime));

            if (x.adjustmentList.get(0).billTime.isBefore(earliest)) {
                earliest = x.adjustmentList.get(0).billTime;
            }

            if (x.adjustmentList.get(x.adjustmentList.size() - 1).billTime.isAfter(latest)) {
                latest = x.adjustmentList.get(x.adjustmentList.size() - 1).billTime;
            }
        }

        for (LocalDate billTime = earliest; !billTime.isAfter(latest); billTime = billTime.plusMonths(1L)) {
            billTimes.add(billTime);
        }
        /**
         * 表头按照字典顺序排序
         */
        Collections.sort(billTimes);
        List<String> times = billTimes.stream().map(x -> x.format(formatter)).collect(Collectors.toList());
        export(data, times, formatter);
    }

    /**
     * 转换导出数据
     *
     * @param data
     * @param times
     * @param formatter
     * @return
     */
    private static List<List<Object>> transferData(List<Billing> data, List<String> times, DateTimeFormatter formatter) {
        List<List<Object>> exportData = new ArrayList<>(data.size());
        int index = 1;
        final Adjustment EMPTY = new Adjustment();

        for (Billing datum : data) {
            Map<String, Adjustment> billingMap = datum.getAdjustmentList().stream()
                    .collect(Collectors.toMap(x -> x.getBillTime().format(formatter), Function.identity()));
            List<Object> row = new ArrayList<>();
            row.add(index++);
            row.add(datum.getInstanceId());

            for (String time : times) {
                Adjustment adjustment = billingMap.getOrDefault(time, EMPTY);
                row.add(adjustment.getOldPrice());
                row.add(adjustment.getNewPrice());
                row.add(adjustment.getDiffPrice());
            }
            exportData.add(row);
        }
        return exportData;
    }

    /**
     * 动态计算表头
     *
     * @param times
     * @return
     */
    private static List<List<String>> getHeaders(List<String> times) {
        List<List<String>> headers = new ArrayList<>();
        headers.add(Lists.newArrayList("序号"));
        headers.add(Lists.newArrayList("实例ID"));

        for (String time : times) {
            headers.add(Lists.newArrayList(time, "老合同金额"));
            headers.add(Lists.newArrayList(time, "新合同金额"));
            headers.add(Lists.newArrayList(time, "金额差"));
        }
        return headers;
    }

    /**
     * 导出Excel
     *
     * @param data
     * @param times
     * @param formatter
     */
    private static void export(List<Billing> data, List<String> times, DateTimeFormatter formatter) {
        String fileName = "/Users/Baltan/Desktop/调账.xls";
        List<List<String>> headers = getHeaders(times);
        List<List<Object>> exportData = transferData(data, times, formatter);
        /**
         * 导出Excel
         */
        EasyExcel.write(fileName)
                .head(headers)
                .sheet("调账数据")
                .doWrite(exportData);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Billing {
        private String instanceId;
        private List<Adjustment> adjustmentList;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Adjustment {
        private LocalDate billTime;
        private double oldPrice;
        private double newPrice;
        private double diffPrice;
    }
}
