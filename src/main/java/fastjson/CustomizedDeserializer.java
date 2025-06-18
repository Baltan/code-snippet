package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Description: 自定义反序列化实现，根据反序列化的类的字段上的不同注解进行区别处理
 *
 * @author Baltan
 * @date 2024/1/25 22:51
 */
public class CustomizedDeserializer {
    public static void main(String[] args) {
        BB bb = new BB()
                .setFoo(new C().setX(1).setY(2))
                .setBar(Lists.newArrayList(
                        new E().setM(new C().setX(12).setY(22)), new E().setM(new C().setX(111).setY(222))
                ));

        String str = JSON.toJSONString(bb);
        System.out.println(str);

        B b = JSON.parseObject(str, B.class);
        System.out.println(JSON.toJSONString(b));
    }

    @Data
    @Accessors(chain = true)
    public static class B {
        @JSONField(deserializeUsing = MyObjectDeserializer.class)
        @WhichOne(key = "x")
        private int foo;
        private List<D> bar;
    }

    @Data
    @Accessors(chain = true)
    public static class BB {
        private C foo;
        private List<E> bar;
    }

    @Data
    @Accessors(chain = true)
    public static class C {
        private int x;
        private int y;
    }

    @Data
    @Accessors(chain = true)
    public static class D {
        @JSONField(deserializeUsing = MyObjectDeserializer.class)
        @WhichOne(key = "y")
        private int m;
    }

    @Data
    @Accessors(chain = true)
    public static class E {
        private C m;
    }

    public static class MyObjectDeserializer implements ObjectDeserializer {
        @SneakyThrows
        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type type, Object o) {
            JSONObject json = parser.parseObject();
            Object object = parser.getContext().object;
            Class<?> clazz = object.getClass();
            Field field = clazz.getDeclaredField((String) o);
            field.setAccessible(true);
            WhichOne whichOne = field.getAnnotation(WhichOne.class);
            field.set(object, json.get(whichOne.key()));
            return null;
        }

        @Override
        public int getFastMatchToken() {
            return 0;
        }
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface WhichOne {
        String key();
    }
}
