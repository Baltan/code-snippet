package optional;

import java.util.Optional;

public class Test3 {
    public static void main(String[] args) {
        // System.out.println(Optional.ofNullable(null).get());
        System.out.println(Optional.ofNullable(null).orElse(0));
        System.out.println(Optional.ofNullable(1).get());
        System.out.println(Optional.ofNullable(1).orElse(0));
    }
}
