package reflect_test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Description: 对指定字段脱敏处理
 *
 * @author Baltan
 * @date 2019-12-16 16:55
 */
public class Test2 {
    private String phoneNo;
    private List<Hobby> hobbies;

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "phoneNo='" + phoneNo + '\'' +
                ", hobbis=" + hobbies +
                '}';
    }

    public static void mask(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Set<String> keyWords = new HashSet<>(Arrays.asList("certcode", "phoneNo", "hobbies", "name"));

        for (Field field : fields) {
            if (keyWords.contains(field.getName())) {
                field.setAccessible(true);
                Object fieldValue = field.get(obj);

                if (fieldValue instanceof String) {
                    String newFieldValue = fieldValue + "******";
                    field.set(obj, newFieldValue);
                } else if (fieldValue instanceof List) {
                    List<Object> list = (List) fieldValue;

                    for (Object ele : list) {
                        mask(ele);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        Test2 t = new Test2();
        t.setPhoneNo("8888888888");
        t.setHobbies(Arrays.asList(new Hobby("eating"), new Hobby("drinking"), new Hobby("playing"),
                new Hobby("sleeping")));
        mask(t);
        System.out.println(t);
    }
}
