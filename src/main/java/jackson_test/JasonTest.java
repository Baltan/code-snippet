package jackson_test;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class JasonTest {
    public static void main(String[] args)
            throws IOException {
        Person p = new Person("zhangsan", 20);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(p);
        System.out.println(json);
        System.out.println("#######################################");
        Person p1 = mapper.readValue(json, Person.class);
        System.out.println(p1.getName());
        System.out.println(p1.getAge());
    }
}
