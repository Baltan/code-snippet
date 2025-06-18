package object;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/6/10 10:42
 */
public class CompareTest {
    public static void main(String[] args) {
        B oldB = new B()
                .setBs("李四")
                .setBi(2)
                .setBc(new C[]{new C().setX("111").setY("222"), new C().setX("333").setY("444")});

        A oldA = new A()
                .setAs("张三")
                .setAi(1)
                .setAb(oldB);

        B newB = new B()
                .setBs("李四")
                .setBi(2)
                .setBc(new C[]{new C().setX("111").setY("222"), new C().setX("333").setY("444")});

        A newA = new A()
                .setAs("张三")
                .setAi(1)
                .setAb(newB);
        System.out.println(compare(oldA, newA));
    }

    /**
     * 判断依据：如果oldJb里不为null的key，newJb里都有一样的value，就认为newJb和oldJb相等
     *
     * @param oldObj
     * @param newObj
     * @return
     */
    static boolean compare(Object oldObj, Object newObj) {
        if (Objects.isNull(oldObj) && !Objects.isNull(newObj)) {
            return false;
        }

        if (!Objects.isNull(oldObj) && Objects.isNull(newObj)) {
            return false;
        }

        if (!Objects.equals(oldObj.getClass(), newObj.getClass())) {
            return false;
        }

        if (oldObj.getClass().isPrimitive() || oldObj instanceof String || oldObj instanceof Boolean ||
                oldObj instanceof Short || oldObj instanceof Character || oldObj instanceof Integer ||
                oldObj instanceof Long || oldObj instanceof Float || oldObj instanceof Double ||
                oldObj instanceof Byte) {
            return Objects.equals(oldObj, newObj);
        } else if (oldObj.getClass().isArray()) {
            Object[] oldArray = (Object[]) oldObj;
            Object[] newArray = (Object[]) newObj;

            if (oldArray.length != newArray.length) {
                return false;
            }

            for (int i = 0; i < oldArray.length; i++) {
                if (!compare(oldArray[i], newArray[i])) {
                    return false;
                }
            }
        } else if (oldObj instanceof Collection) {
            Collection oldCol = (Collection) oldObj;
            Collection newCol = (Collection) newObj;

            if (oldCol.size() != newCol.size()) {
                return false;
            }

            Iterator oldIt = ((Iterable<?>) oldObj).iterator();
            Iterator newIt = ((Iterable<?>) newObj).iterator();

            while (oldIt.hasNext()) {
                Object oldEle = oldIt.next();
                Object newEle = newIt.next();

                if (!compare(oldEle, newEle)) {
                    return false;
                }
            }
        } else {
            JSONObject oldJb = JSONObject.parseObject(JSONObject.toJSONString(oldObj));
            JSONObject newJb = JSONObject.parseObject(JSONObject.toJSONString(newObj));

            for (Map.Entry<String, Object> entry : oldJb.entrySet()) {
                String key = entry.getKey();
                Object oldValue = entry.getValue();
                Object newValue = newJb.get(key);

                if (!compare(oldValue, newValue)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Data
    @Accessors(chain = true)
    static class A {
        String as;
        Integer ai;
        B ab;
    }

    @Data
    @Accessors(chain = true)
    static class B {
        String bs;
        Integer bi;
        C[] bc;
    }

    @Data
    @Accessors(chain = true)
    static class C {
        String x;
        String y;
    }
}
