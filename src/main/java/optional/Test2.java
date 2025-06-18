package optional_test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/4/17 12:40
 * 参考：<a href="https://mp.weixin.qq.com/s/UBM9fJFrak18PBtxmAorwQ"></a>
 */
public class Test2 {
    public static void main(String[] args) {
        User user1 = Optional.ofNullable((User) null).orElse(createUser());
        System.out.println(user1);

        User user2 = Optional.ofNullable((User) null).orElseGet(() -> createUser());
        System.out.println(user2);

        try {
            User user3 = Optional.ofNullable((User) null).orElseThrow(() -> new NullPointerException());
            System.out.println(user3);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        User user4 = Optional.ofNullable(new User("zhangsan")).orElse(createUser());
        System.out.println(user4);

        User user5 = Optional.ofNullable(new User("zhangsan")).orElseGet(() -> createUser());
        System.out.println(user5);

        try {
            User user6 =
                    Optional.ofNullable(new User("zhangsan")).orElseThrow(() -> new NullPointerException());
            System.out.println(user6);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static User createUser() {
        return new User("lisi");
    }

    @Data
    @AllArgsConstructor
    public static class User {
        private String name;
    }
}
