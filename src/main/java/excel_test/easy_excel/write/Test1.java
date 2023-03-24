package excel_test.easy_excel.write;

import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Description:
 *
 * @author baltan
 * @date 2023/3/25 01:34
 */
public class Test1 {
    public static void main(String[] args) {
        List<Bill> data = mockData();
        process(data);
    }

    private static <T> void process(List<T> data) {
        final String FILENAME = "/Users/Baltan/Desktop/调账.xls";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final Object EMPTY_OBJ = new Object();

        if (CollectionUtils.isEmpty(data)) {
            throw new RuntimeException("无导出数据");
        }
        Class<T> clazz = getGenericClass(data);
        List<Field> fields = getFields(clazz);
        Field identifierField = getIdentifierField(fields);
        Field timeField = getTimeField(fields);
        Field[] priceFields = getPriceFields(fields);
        /**
         * 资源标识符 -> {账期 -> 数据}
         */
        Map<Object, Map<Object, T>> dataMap = dataGroup(EMPTY_OBJ, data, identifierField, timeField);
        String earliest = getEarliest(dataMap);
        String latest = getLatest(dataMap);
        List<LocalDate> billTimes = getAllBillTimes(earliest, latest, formatter);
        Excel excel = render(fields, billTimes, priceFields, dataMap, formatter);
        export(FILENAME, excel);
    }

    /**
     * 获取导出数据实体类Class对象
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Class<T> getGenericClass(List<T> data) {
        return (Class<T>) data.get(0).getClass();
    }

    /**
     * 获取导出数据实体类字段
     *
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T> List<Field> getFields(Class<T> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(x -> x.isAnnotationPresent(ExcelHeader.class))
                .peek(x -> x.setAccessible(true))
                .sorted((x, y) -> x.getAnnotation(ExcelHeader.class).order() - x.getAnnotation(ExcelHeader.class).order())
                .collect(Collectors.toList());
    }

    /**
     * 获取导出数据分组字段
     *
     * @param fields
     * @return
     */
    private static Field getIdentifierField(List<Field> fields) {
        Field[] identifierFields = fields.stream()
                .filter(x -> Objects.equals(x.getAnnotation(ExcelHeader.class).type(), ValueType.IDENTIFIER))
                .toArray(Field[]::new);

        if (identifierFields.length != 1) {
            throw new RuntimeException("资源标识符不唯一");
        }
        return identifierFields[0];
    }

    /**
     * 获取调账数据时间字段
     *
     * @param fields
     * @return
     */
    private static Field getTimeField(List<Field> fields) {
        Field[] timeFields = fields.stream()
                .filter(x -> Objects.equals(x.getAnnotation(ExcelHeader.class).type(), ValueType.BILL_TIME))
                .toArray(Field[]::new);

        if (timeFields.length != 1) {
            throw new RuntimeException("账期字段不唯一");
        }
        return timeFields[0];
    }

    /**
     * 获取调账数据价格字段
     *
     * @param fields
     * @return
     */
    private static Field[] getPriceFields(List<Field> fields) {
        return fields.stream()
                .filter(x -> Objects.equals(x.getAnnotation(ExcelHeader.class).type(), ValueType.PRICE))
                .sorted(Comparator.comparingInt(x -> x.getAnnotation(ExcelHeader.class).order()))
                .toArray(Field[]::new);
    }

    /**
     * 对导出数据按照资源标识符分组
     *
     * @param EMPTY_OBJ
     * @param data
     * @param identifierField
     * @param timeField
     * @param <T>
     * @return
     */
    private static <T> Map<Object, Map<Object, T>> dataGroup(final Object EMPTY_OBJ, List<T> data, Field identifierField, Field timeField) {
        return data.stream().collect(Collectors.groupingBy(x -> {
            try {
                return identifierField.get(x);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return EMPTY_OBJ;
        }, Collectors.toMap(x -> {
            try {
                return timeField.get(x);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return EMPTY_OBJ;
        }, Function.identity(), (m, n) -> m)));
    }

    /**
     * 获取导出数据的最早账期时间
     *
     * @param <T>
     * @param dataMap
     * @return
     */
    private static <T> String getEarliest(Map<Object, Map<Object, T>> dataMap) {
        return dataMap.values().stream()
                .flatMap(x -> x.keySet().stream())
                .map(x -> String.valueOf(x))
                .min(String::compareTo)
                .get();
    }

    /**
     * 获取导出数据的最晚账期时间
     *
     * @param <T>
     * @param dataMap
     * @return
     */
    private static <T> String getLatest(Map<Object, Map<Object, T>> dataMap) {
        return dataMap.values().stream()
                .flatMap(x -> x.keySet().stream())
                .map(x -> String.valueOf(x))
                .max(String::compareTo)
                .get();
    }

    /**
     * 获取导出Excel中所有的账期时间表头
     *
     * @param earliest
     * @param latest
     * @param formatter
     * @return
     */
    private static List<LocalDate> getAllBillTimes(String earliest, String latest, final DateTimeFormatter formatter) {
        List<LocalDate> billTimes = new ArrayList<>();

        for (LocalDate start = LocalDate.parse(earliest, formatter), end = LocalDate.parse(latest, formatter), billTime = start;
             !billTime.isAfter(end); billTime = billTime.plusMonths(1L)) {
            billTimes.add(billTime);
        }
        /**
         * 按照字典顺序排序
         */
        Collections.sort(billTimes);
        return billTimes;
    }

    /**
     * 渲染导出的Excel
     *
     * @param fields
     * @param billTimes
     * @param priceFields
     * @param dataMap
     * @param formatter
     * @return
     */
    private static <T> Excel render(List<Field> fields, List<LocalDate> billTimes, Field[] priceFields, Map<Object, Map<Object, T>> dataMap, final DateTimeFormatter formatter) {
        List<List<String>> headers = new ArrayList<>();
        List<List<Object>> exportData = IntStream.range(0, dataMap.size())
                .boxed()
                .map(x -> new ArrayList<>())
                .collect(Collectors.toList());
        fields.stream()
                .forEach(x -> {
                    ExcelHeader annotation = x.getAnnotation(ExcelHeader.class);
                    switch (annotation.type()) {
                        case ORDER:
                            headers.add(Lists.newArrayList(annotation.value()));
                            IntStream.range(0, dataMap.size())
                                    .forEach(i -> exportData.get(i).add(i + 1));
                            break;
                        case BASE_INFO:
                        case IDENTIFIER:
                            headers.add(Lists.newArrayList(annotation.value()));
                            int i = 0;
                            for (Map<Object, T> groupByTime : dataMap.values()) {
                                T record = groupByTime.values().iterator().next();
                                try {
                                    exportData.get(i).add(x.get(record));
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                i++;
                            }
                            break;
                        case BILL_TIME:
                            billTimes.stream()
                                    .map(y -> y.format(formatter))
                                    .forEach(y -> Arrays.stream(priceFields)
                                            .forEach(z -> {
                                                headers.add(Lists.newArrayList(y, z.getAnnotation(ExcelHeader.class).value()));
                                                int j = 0;
                                                for (Map<Object, T> groupByTime : dataMap.values()) {
                                                    if (groupByTime.containsKey(y)) {
                                                        try {
                                                            exportData.get(j).add(z.get(groupByTime.get(y)));
                                                        } catch (IllegalAccessException e) {
                                                            e.printStackTrace();
                                                        }
                                                    } else {
                                                        exportData.get(j).add(null);
                                                    }
                                                    j++;
                                                }
                                            }));
                    }
                });
        return new Excel(headers, exportData);
    }

    /**
     * 导出Excel
     *
     * @param FILENAME
     * @param excel
     */
    private static void export(final String FILENAME, Excel excel) {
        EasyExcel.write(FILENAME)
                .head(excel.headers)
                .sheet("调账数据")
                .doWrite(excel.exportData);
    }

    /**
     * 生成调账测试数据
     *
     * @return
     */
    private static List<Bill> mockData() {
        return Arrays.asList(
                new Bill()
                        .setInstanceId("aaaa")
                        .setInstanceClass("bbbb")
                        .setEngineVersion("cccc")
                        .setArchitectureType("dddd")
                        .setBillTime("2023-01-01")
                        .setOldContractAmount(BigDecimal.valueOf(9))
                        .setNewContractAmount(BigDecimal.valueOf(5))
                        .setBalanceAmount(BigDecimal.valueOf(2)),
                new Bill()
                        .setInstanceId("aaaa")
                        .setInstanceClass("bbbb")
                        .setEngineVersion("cccc")
                        .setArchitectureType("dddd")
                        .setBillTime("2023-02-01")
                        .setOldContractAmount(BigDecimal.valueOf(3))
                        .setNewContractAmount(BigDecimal.valueOf(8))
                        .setBalanceAmount(BigDecimal.valueOf(7)),
                new Bill()
                        .setInstanceId("aaaa")
                        .setInstanceClass("bbbb")
                        .setEngineVersion("cccc")
                        .setArchitectureType("dddd")
                        .setBillTime("2023-03-01")
                        .setOldContractAmount(BigDecimal.valueOf(7))
                        .setNewContractAmount(BigDecimal.valueOf(5))
                        .setBalanceAmount(BigDecimal.valueOf(9)),
                new Bill()
                        .setInstanceId("aaaa")
                        .setInstanceClass("bbbb")
                        .setEngineVersion("cccc")
                        .setArchitectureType("dddd")
                        .setBillTime("2023-06-01")
                        .setOldContractAmount(BigDecimal.valueOf(4))
                        .setNewContractAmount(BigDecimal.valueOf(2))
                        .setBalanceAmount(BigDecimal.valueOf(7)),
                new Bill()
                        .setInstanceId("xxxx")
                        .setInstanceClass("yyyy")
                        .setEngineVersion("zzzz")
                        .setArchitectureType("dddd")
                        .setBillTime("2022-12-01")
                        .setOldContractAmount(BigDecimal.valueOf(0))
                        .setNewContractAmount(BigDecimal.valueOf(1))
                        .setBalanceAmount(BigDecimal.valueOf(10)),
                new Bill()
                        .setInstanceId("xxxx")
                        .setInstanceClass("yyyy")
                        .setEngineVersion("zzzz")
                        .setArchitectureType("dddd")
                        .setBillTime("2023-01-01")
                        .setOldContractAmount(BigDecimal.valueOf(0))
                        .setNewContractAmount(BigDecimal.valueOf(8))
                        .setBalanceAmount(BigDecimal.valueOf(7)),
                new Bill()
                        .setInstanceId("xxxx")
                        .setInstanceClass("yyyy")
                        .setEngineVersion("zzzz")
                        .setArchitectureType("dddd")
                        .setBillTime("2023-03-01")
                        .setOldContractAmount(BigDecimal.valueOf(7))
                        .setNewContractAmount(BigDecimal.valueOf(5))
                        .setBalanceAmount(BigDecimal.valueOf(9)),
                new Bill()
                        .setInstanceId("xxxx")
                        .setInstanceClass("yyyy")
                        .setEngineVersion("zzzz")
                        .setArchitectureType("dddd")
                        .setBillTime("2023-02-01")
                        .setOldContractAmount(BigDecimal.valueOf(2))
                        .setNewContractAmount(BigDecimal.valueOf(3))
                        .setBalanceAmount(BigDecimal.valueOf(9))
        );
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    private static class Bill {
        @ExcelHeader(value = "序号", order = 0, type = ValueType.ORDER)
        private int order;
        @ExcelHeader(value = "实例ID", order = 1, type = ValueType.IDENTIFIER)
        private String instanceId;
        @ExcelHeader(value = "实例规格", order = 2, type = ValueType.BASE_INFO)
        private String instanceClass;
        @ExcelHeader(value = "引擎版本", order = 3, type = ValueType.BASE_INFO)
        private String engineVersion;
        @ExcelHeader(value = "架构类型", order = 4, type = ValueType.BASE_INFO)
        private String architectureType;
        @ExcelHeader(value = "账期", order = 5, type = ValueType.BILL_TIME)
        private String billTime;
        @ExcelHeader(value = "老合同出账金额", order = 6, type = ValueType.PRICE)
        private BigDecimal oldContractAmount;
        @ExcelHeader(value = "新合同出账金额", order = 7, type = ValueType.PRICE)
        private BigDecimal newContractAmount;
        @ExcelHeader(value = "差异金额", order = 8, type = ValueType.PRICE)
        private BigDecimal balanceAmount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Excel {
        private List<List<String>> headers;
        private List<List<Object>> exportData;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    private @interface ExcelHeader {
        @NotBlank
        String value();

        @NotEmpty
        int order();

        @NotNull
        ValueType type();
    }

    private enum ValueType {
        /**
         * 序号，唯一
         */
        ORDER,
        /**
         * 资源标识符，唯一
         */
        IDENTIFIER,
        /**
         * 资源基本信息
         */
        BASE_INFO,
        /**
         * 账期，唯一
         */
        BILL_TIME,
        /**
         * 价格
         */
        PRICE
    }
}
