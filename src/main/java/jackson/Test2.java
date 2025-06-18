package jackson_test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;


public class Test2 {
    public static void main(String[] args)
            throws IOException {
        List<String> hobbies = new ArrayList<String>();
        hobbies.add("reading");
        hobbies.add("watching movies");
        hobbies.add("football");
        Map<String, String> address = new HashMap<String, String>();
        address.put("province", "Zhejiang");
        address.put("city", "Hangzhou");
        address.put("district", "Gongchen");
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("name", "zhangsan");
        m.put("age", 20);
        m.put("ifMarried", false);
        m.put("hobbies", hobbies);
        m.put("address", address);
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("张三", 20));
        persons.add(new Person("李四", 19));
        persons.add(new Person("王五", 21));
        Set<Person> set = new HashSet<>();
        set.add(new Person("张三", 20));
        set.add(new Person("李四", 19));
        set.add(new Person("王五", 21));
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(m);
        String personsStr = mapper.writeValueAsString(persons);
        String personStr1 = mapper.writeValueAsString(set);
        System.out.println(str);
        System.out.println(personsStr);
        System.out.println(personStr1);
    }
}
