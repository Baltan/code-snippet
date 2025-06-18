package fastjson_test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/6 16:54
 */
public class Test1 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", null);
        String mapJson = JSON.toJSONString(map);
        System.out.println(mapJson);
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
        System.out.println(JSON.toJSONString(map, SerializerFeature.UseSingleQuotes));
        System.out.println(JSON.toJSONString(map, true));
        System.out.println("**********************************************************");

        List<String> list = Arrays.asList(new String[]{"value1", "value2", "value3", "value4"});
        String listJson = JSON.toJSONString(list);
        System.out.println(listJson);
        System.out.println(JSON.toJSONString(list, SerializerFeature.UseSingleQuotes));
        System.out.println(JSON.toJSONString(list, true));
        System.out.println("**********************************************************");

        Person p1 = new Person("zhangsan", "male", 20);
        System.out.println(p1);
        System.out.println("**********************************************************");

        String p1Json = JSON.toJSONString(p1);
        System.out.println(p1Json);
        System.out.println(JSON.toJSONString(p1, SerializerFeature.UseSingleQuotes));
        System.out.println(JSON.toJSONString(p1, true));
        System.out.println("**********************************************************");

        // 将Date转化为long类型
        System.out.println(JSON.toJSONString(new Date()));
        System.out.println("**********************************************************");

        // 格式化日期
        System.out.println(JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat));
        System.out.println("**********************************************************");

        // 格式化日期
        System.out.println(JSON.toJSONStringWithDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println("**********************************************************");

        Person p2 = new Person("lisi", "male", 30);
        // 打开autotype功能，否则JSON.parse反序列化会报错，如果有使用非全局ParserConfig则用另外调用setAutoTypeSupport(true)
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        String p2Json = JSON.toJSONString(p2, SerializerFeature.WriteClassName);
        System.out.println(p2Json);
        // 若序列化时没有添加类型信息，即SerializerFeature.WriteClassName，JSON.parse反序列化会报错
        Person p3 = (Person) JSON.parse(p2Json);
        System.out.println(p3.getName());
        System.out.println("**********************************************************");

        Person p4 = JSON.parseObject(p1Json, Person.class);
        System.out.println(p4.getName());
        System.out.println("**********************************************************");

        List<String> list1 = JSON.parseObject(listJson, List.class);
        System.out.println(list1);
        List<String> list2 = JSON.parseArray(listJson, String.class);
        System.out.println(list2);
        List<String> list3 = JSON.parseObject(listJson, new TypeReference<List<String>>() {
        });
        System.out.println(list3);
        System.out.println("**********************************************************");
    }
}
