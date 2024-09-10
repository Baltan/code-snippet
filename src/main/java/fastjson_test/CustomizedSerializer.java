package fastjson_test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.filter.AfterFilter;
import com.alibaba.fastjson2.filter.Filter;
import com.alibaba.fastjson2.filter.NameFilter;
import com.alibaba.fastjson2.filter.ValueFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Description: 自定义序列化实现，根据序列化的类的字段上的不同注解进行区别处理
 *
 * @author baltan
 * @date 2024/9/10 10:51
 */
public class CustomizedSerializer {
    public static void main(String[] args) {
        Config config = JSON.parseObject("{\"from\":1,\"internetIp\":\"1.1.1.1\",\"mappingList\":[{\"intranetIp\":\"11.11.11.11\",\"intranetPort\":\"8888/8888\",\"internetPorts\":[\"7777/7777\"],\"protocol\":\"TCP/UDP\"},{\"intranetIp\":\"22.22.22.22\",\"internetPorts\":[\"-1/-1\"],\"protocol\":\"ICMP\"}]}", Config.class);
        System.out.println(config.serialize());
    }

    /**
     * Description: 来源枚举
     *
     * @author baltan
     * @date 2024/9/4 16:40
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum FromEnum {
        PORTAL_APPLICATION(1, "门户申请"),
        BACKEND_APPLICATION(2, "后台申请"),
        BACKEND_PROXY_APPLICATION(3, "后台代申请"),
        ;
        private final int type;
        private final String typeCh;

        public static String getTypeChByType(Integer type) {
            return Arrays.stream(values())
                    .filter(x -> ObjectUtil.equals(x.type, type))
                    .findFirst()
                    .map(FromEnum::getTypeCh)
                    .orElse(null);
        }
    }

    /**
     * Description: 操作类型枚举
     *
     * @author baltan
     * @date 2024/9/4 16:11
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum OperationEnum {
        ADD(1, "新增"),
        REMOVE(2, "删除"),
        UNCHANGED(3, "不变"),
        MODIFY(4, "修改");
        private final int type;
        private final String typeCh;

        public static String getTypeChByType(Integer type) {
            return Arrays.stream(values())
                    .filter(x -> ObjectUtil.equals(x.type, type))
                    .findFirst()
                    .map(OperationEnum::getTypeCh)
                    .orElse(null);
        }
    }

    /**
     * Description: 序列化value调用方法枚举
     *
     * @author baltan
     * @date 2024/9/6 10:53
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    public enum ValueSerializeMethodEnum {
        RESOURCE_SOURCE_CH(x -> FromEnum.getTypeChByType((Integer) x)),
        OPERATION_CH(x -> OperationEnum.getTypeChByType((Integer) x));
        private final Function<Object, Object> function;
    }

    /**
     * Description: 序列化key转换规则注解
     *
     * @author baltan
     * @date 2024/1/25 15:35
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface KeyTransfer {
        String keyCh();

        String description() default "";
    }

    /**
     * Description: 序列化value转换规则注解
     *
     * @author baltan
     * @date 2024/1/25 15:35
     */
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValueTransfer {
        ValueSerializeMethodEnum valueSerializeMethodEnum();
    }

    /**
     * Description: 序列化key转换过滤器
     *
     * @author baltan
     * @date 2024/1/25 15:21
     */
    public class KeyTransferFilter implements NameFilter {
        /**
         * @param object
         * @param fieldName
         * @param fieldValue
         * @return
         */
        @Override
        public String process(Object object, String fieldName, Object fieldValue) {
            /**
             * 序列化时，需要被处理的字段
             */
            Field field = ReflectUtil.getField(object.getClass(), fieldName);
            field.setAccessible(true);
            return Optional.ofNullable(field.getAnnotation(KeyTransfer.class))
                    .map(x -> StrUtil.isBlank(x.keyCh()) ? fieldName : x.keyCh())
                    .orElse(fieldName);
        }
    }

    /**
     * Description: 序列化value转换过滤器
     *
     * @author baltan
     * @date 2024/9/5 16:23
     */
    public class ValueTransferFilter implements ValueFilter {
        /**
         * @param object
         * @param fieldName
         * @param fieldValue
         * @return
         */
        @Override
        public Object apply(Object object, String fieldName, Object fieldValue) {
            /**
             * 序列化时，需要被处理的字段
             */
            Field field = ReflectUtil.getField(object.getClass(), fieldName);
            field.setAccessible(true);
            return Optional.ofNullable(fieldValue)
                    .map(value -> Optional.ofNullable(field.getAnnotation(ValueTransfer.class))
                            .map(annotation -> Optional.ofNullable(annotation.valueSerializeMethodEnum())
                                    .map(y -> {
                                        try {
                                            return annotation.valueSerializeMethodEnum().getFunction().apply(fieldValue);
                                        } catch (Exception e) {
                                            return fieldValue;
                                        }
                                    })
                                    .orElse(fieldValue))
                            .orElse(fieldValue))
                    .orElse(fieldValue);
        }
    }

    /**
     * Description: 序列化后置处理过滤器
     *
     * @author baltan
     * @date 2024/9/8 18:47
     */
    public class AfterDoFilter extends AfterFilter {
        /**
         * @param object
         */
        @Override
        public void writeAfter(Object object) {
            Config config = (Config) object;
            if (CollUtil.isNotEmpty(config.getMappingList())) {
                for (int i = 1; i <= config.getMappingList().size(); i++) {
                    Config.Mapping mapping = config.getMappingList().get(i - 1);
                    String value = String.format("内网IP：%s 内部端口：%s 协议：%s 公网端口：%s",
                            Optional.ofNullable(mapping.getIntranetIp()).orElse("--"),
                            Optional.ofNullable(mapping.getIntranetPort()).orElse("--"),
                            Optional.ofNullable(mapping.getProtocol()).orElse("--"),
                            Optional.ofNullable(mapping.getInternetPorts()).orElse(Collections.emptyList()));
                    writeKeyValue(String.format("映射%d", i), value);
                }
            }
        }
    }

    /**
     * Description: 配置类
     *
     * @author baltan
     * @date 2024/9/5 09:46
     */
    @Data
    @Accessors(chain = true)
    public class Config {
        @KeyTransfer(keyCh = "公网IP")
        @JSONField(ordinal = 1)
        private String internetIp;
        @KeyTransfer(keyCh = "来源", description = "1-门户申请，2-后台申请，3-后台代申请")
        @ValueTransfer(valueSerializeMethodEnum = ValueSerializeMethodEnum.RESOURCE_SOURCE_CH)
        @JSONField(ordinal = 2)
        private Integer from;
        @KeyTransfer(keyCh = "映射列表")
        @JSONField(serialize = false)
        private List<Mapping> mappingList;

        @Data
        @Accessors(chain = true)
        public class Mapping {
            @KeyTransfer(keyCh = "内网IP")
            @JSONField(ordinal = 2)
            private String intranetIp;
            @KeyTransfer(keyCh = "内部端口", description = "格式为：起始内部端口/结束内部端口")
            @JSONField(ordinal = 5)
            private String intranetPort;
            @KeyTransfer(keyCh = "公网端口", description = "每一个公网端口格式为：起始公网端口/结束公网端口")
            @JSONField(ordinal = 6)
            private List<String> internetPorts;
            @KeyTransfer(keyCh = "协议", description = "TCP，UDP，IP，ICMP，TCP/UDP，ANY")
            @JSONField(ordinal = 7)
            private String protocol;
            @KeyTransfer(keyCh = "操作", description = "1-新增，2-删除，3-不变，4-修改")
            @ValueTransfer(valueSerializeMethodEnum = ValueSerializeMethodEnum.OPERATION_CH)
            @JSONField(ordinal = 8)
            private Integer operate;
        }

        /**
         * 自定义序列化
         *
         * @return
         */
        public String serialize() {
            return JSON.toJSONString(this, new Filter[]{new KeyTransferFilter(), new ValueTransferFilter(), new AfterDoFilter()});
        }
    }
}
