package reflect_test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/6/10 10:44
 */
public class Test3 {
    public static void main(String[] args) throws Exception {
        // String user = "{\"name\":\"啦啦啦\"}";
        String user = "[{\"name\":\"啦啦啦\"},{\"name\":\"噜噜噜\"}]";

        Class feignClazz = Class.forName("Test3$Feign");
        // Object bean = SpringUtils.getBean("Test3$Feign");
        Object bean = feignClazz.newInstance();

        // 取内部类用"$"分隔符
        Class userClazz = Class.forName("Test3$User");

        if (user.startsWith("{") && user.endsWith("}")) {
            Object o = JSONObject.parseObject(user, userClazz);
            String[] params = {"Test3$User"};
            Class[] paramClasses = new Class[params.length];

            for (int i = 0; i < params.length; i++) {
                paramClasses[i] = Class.forName(params[i]);
            }
            // Method[] methods = feignClazz.getDeclaredMethods();
            // Method method =
            //         Arrays.stream(methods)
            //                 .filter(func -> Objects.equals(func.getName(), "getName"))
            //                 .findFirst().get();
            Method method = feignClazz.getDeclaredMethod("getName", paramClasses);
            Object result = method.invoke(bean, o);
            System.out.println(result);
        } else if (user.startsWith("[") && user.endsWith("]")) {
            Object o = JSONArray.parseArray(user, userClazz);
            String[] params = {"java.util.List"};
            Class[] paramClasses = new Class[params.length];

            for (int i = 0; i < params.length; i++) {
                paramClasses[i] = Class.forName(params[i]);
            }
            // Method[] methods = feignClazz.getDeclaredMethods();
            // Method method =
            //         Arrays.stream(methods)
            //                 .filter(func -> Objects.equals(func.getName(), "getNames"))
            //                 .findFirst().get();
            Method method = feignClazz.getDeclaredMethod("getNames", paramClasses);
            Object result = method.invoke(bean, o);
            System.out.println(result);
        } else {
            Object o = user;
        }
    }

    @Data
    @Accessors(chain = true)
    static class Feign {
        String getName(User user) {
            return user.name;
        }

        List<String> getNames(List<User> userList) {
            return userList.stream().map(User::getName).collect(Collectors.toList());
        }
    }

    @Data
    @Accessors(chain = true)
    static class User {
        String name;
    }
}
