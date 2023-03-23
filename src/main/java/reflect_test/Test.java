package reflect_test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author baltan
 * @date 2023/3/22 16:13
 * @see <a href="https://blog.csdn.net/blueheartstone/article/details/113512479"></a>
 * @see <a href="https://cloud.tencent.com/developer/article/1489861"></a>
 * @see <a href="https://cloud.tencent.com/developer/ask/sof/127737"></a>
 * @see <a href="328f060b099044919261fd923"></a>
 */
public class Test {
    @SneakyThrows
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
         * 要增加的表头，按照字典顺序排序
         */
        Set<LocalDate> billTimes = new TreeSet<>();
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
        String clazzName = "reflect_test.BingeV5";
        MyField[] fields = {new MyField<>("order", Integer.class, new String[]{"序号"}), new MyField<>("instanceId", String.class, new String[]{"实例ID"})};
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = createClazz(classPool, clazzName);
        clazzAddAnnotation(ctClass, Data.class, Collections.emptyMap());
        clazzAddNoArgsConstructor(ctClass);
        Map<String, Object> attributeMap;

        for (MyField field : fields) {
            attributeMap = new HashMap<>();
            attributeMap.put("value", field.getAnnotationValue());
            clazzAddField(classPool, ctClass, field.getName(), field.getType(), attributeMap);
        }

        for (LocalDate billTime : billTimes) {
            String time = billTime.format(formatter);
            attributeMap = new HashMap<>();
            attributeMap.put("value", new String[]{time, "旧合同价格"});
            clazzAddField(classPool, ctClass, "oldPrice" + time, Double.class, attributeMap);
            attributeMap = new HashMap<>();
            attributeMap.put("value", new String[]{time, "新合同价格"});
            clazzAddField(classPool, ctClass, "newPrice" + time, Double.class, attributeMap);
            attributeMap = new HashMap<>();
            attributeMap.put("value", new String[]{time, "价格差"});
            clazzAddField(classPool, ctClass, "diffPrice" + time, Double.class, attributeMap);
        }
        /**
         * 输出.Class文件到指定路径
         */
        ctClass.writeFile("/Users/Baltan/Desktop");

        Class exportClazz = ctClass.toClass();
        List exportList = fillData(data, exportClazz, formatter);
        String fileName = "/Users/Baltan/Desktop/调账.xls";
        EasyExcel.write(fileName, exportClazz).sheet("调账数据").doWrite(exportList);
    }

    /**
     * 填充要导出的数据
     *
     * @param data
     * @param exportClazz
     * @param formatter
     * @return
     */
    @SneakyThrows
    private static <T> List<T> fillData(List<Billing> data, Class<T> exportClazz, DateTimeFormatter formatter) {
        List<T> exportList = new ArrayList<>(data.size());
        Field field;
        int index = 1;

        for (Billing datum : data) {
            T instance = exportClazz.newInstance();
            field = exportClazz.getDeclaredField("order");
            field.setAccessible(true);
            field.set(instance, index++);
            field = exportClazz.getDeclaredField("instanceId");
            field.setAccessible(true);
            field.set(instance, datum.getInstanceId());

            if (CollectionUtils.isEmpty(datum.getAdjustmentList())) {
                continue;
            }
            Map<LocalDate, Adjustment> billingMap = datum.getAdjustmentList().stream()
                    .collect(Collectors.toMap(Adjustment::getBillTime, Function.identity()));

            for (Adjustment adjustment : datum.getAdjustmentList()) {
                if (!billingMap.containsKey(adjustment.getBillTime())) {
                    continue;
                }
                String time = adjustment.getBillTime().format(formatter);
                field = exportClazz.getDeclaredField("oldPrice" + time);
                field.setAccessible(true);
                field.set(instance, adjustment.getOldPrice());
                field = exportClazz.getDeclaredField("newPrice" + time);
                field.setAccessible(true);
                field.set(instance, adjustment.getNewPrice());
                field = exportClazz.getDeclaredField("diffPrice" + time);
                field.setAccessible(true);
                field.set(instance, adjustment.getDiffPrice());
            }
            exportList.add(instance);
        }
        return exportList;
    }

    /**
     * 为注解动态添加属性
     *
     * @param constPool
     * @param annotation
     * @param attributeMap
     */
    private static void setAnnotationAttributes(ConstPool constPool, Annotation annotation, Map<String, Object> attributeMap) {
        if (MapUtils.isEmpty(attributeMap)) {
            return;
        }
        for (Map.Entry<String, Object> entry : attributeMap.entrySet()) {
            String attribute = entry.getKey();
            Object value = entry.getValue();
            MemberValue memberValue;

            if (value instanceof String) {
                memberValue = new StringMemberValue((String) value, constPool);
            } else if (value instanceof Byte) {
                memberValue = new ByteMemberValue((Byte) value, constPool);
            } else if (value instanceof Short) {
                memberValue = new ShortMemberValue((Short) value, constPool);
            } else if (value instanceof Integer) {
                memberValue = new IntegerMemberValue((Integer) value, constPool);
            } else if (value instanceof Long) {
                memberValue = new LongMemberValue((Long) value, constPool);
            } else if (value instanceof Float) {
                memberValue = new FloatMemberValue((Float) value, constPool);
            } else if (value instanceof Double) {
                memberValue = new DoubleMemberValue((Double) value, constPool);
            } else if (value instanceof Character) {
                memberValue = new CharMemberValue((Character) value, constPool);
            } else if (value instanceof Boolean) {
                memberValue = new BooleanMemberValue((Boolean) value, constPool);
            } else if (value.getClass().isArray()) {
                Object[] objects = (Object[]) value;
                memberValue = new ArrayMemberValue(constPool);
                ArrayMemberValue arrayMemberValue = (ArrayMemberValue) memberValue;
                arrayMemberValue.setValue(Arrays.stream(objects)
                        .map(x -> new StringMemberValue((String) x, constPool))
                        .toArray(MemberValue[]::new));
            } else {
                continue;
            }
            annotation.addMemberValue(attribute, memberValue);
        }
    }

    /**
     * 为属性动态添加注解
     *
     * @param ctField
     * @param annotationClazz
     * @param attributeMap
     * @param <T>
     */
    private static <T> void fieldAddAnnotation(CtField ctField, Class<T> annotationClazz, Map<String, Object> attributeMap) {
        FieldInfo fieldInfo = ctField.getFieldInfo();
        ConstPool constPool = fieldInfo.getConstPool();
        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.invisibleTag);
        Annotation annotation = new Annotation(annotationClazz.getName(), constPool);
        setAnnotationAttributes(constPool, annotation, attributeMap);
        annotationsAttribute.addAnnotation(annotation);
        fieldInfo.addAttribute(annotationsAttribute);
    }

    /**
     * 为类动态添加注解
     *
     * @param ctClass
     * @param annotationClazz
     * @param attributeMap
     * @param <T>
     */
    private static <T> void clazzAddAnnotation(CtClass ctClass, Class<T> annotationClazz, Map<String, Object> attributeMap) {
        ClassFile classFile = ctClass.getClassFile();
        ConstPool constPool = classFile.getConstPool();
        AnnotationsAttribute annotationsAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation(annotationClazz.getName(), constPool);
        setAnnotationAttributes(constPool, annotation, attributeMap);
        annotationsAttribute.addAnnotation(annotation);
        classFile.addAttribute(annotationsAttribute);
    }

    /**
     * 为类动态添加属性
     *
     * @param classPool
     * @param targetClazz
     * @param fieldName
     * @param fieldType
     * @param attributeMap
     * @param <T>
     */
    @SneakyThrows
    private static <T> void clazzAddField(ClassPool classPool, CtClass targetClazz, String fieldName, Class<T> fieldType, Map<String, Object> attributeMap) {
        CtClass fieldClazz = classPool.get(fieldType.getName());
        CtField ctField = new CtField(fieldClazz, fieldName, targetClazz);
        ctField.setModifiers(Modifier.PRIVATE);
        targetClazz.addField(ctField);
        fieldAddAnnotation(ctField, ExcelProperty.class, attributeMap);
        clazzAddGetterSetter(targetClazz, ctField, fieldName);
    }

    /**
     * 为字段动态添加Getter/Setter
     *
     * @param targetClazz
     * @param ctField
     * @param fieldName
     */
    @SneakyThrows
    private static void clazzAddGetterSetter(CtClass targetClazz, CtField ctField, String fieldName) {
        String setterMethodName = "set" + (fieldName.substring(0, 1).toUpperCase()) + fieldName.substring(1);
        String getterMethodName = "get" + (fieldName.substring(0, 1).toUpperCase()) + fieldName.substring(1);
        targetClazz.addMethod(CtNewMethod.setter(setterMethodName, ctField));
        targetClazz.addMethod(CtNewMethod.getter(getterMethodName, ctField));
    }

    /**
     * 为类动态添加无参构造方法
     *
     * @param targetClazz
     */
    @SneakyThrows
    private static void clazzAddNoArgsConstructor(CtClass targetClazz) {
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, targetClazz);
        ctConstructor.setBody("{}");
        targetClazz.addConstructor(ctConstructor);
    }

    /**
     * 动态创建类
     *
     * @param classPool
     * @param clazzName
     * @return
     */
    private static CtClass createClazz(ClassPool classPool, String clazzName) {
        return classPool.makeClass(clazzName);
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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class MyField<T> {
        private String name;
        private Class<T> type;
        private Object annotationValue;
    }
}
