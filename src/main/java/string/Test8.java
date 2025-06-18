package string;

import java.util.Arrays;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-02-14 19:40
 */
public class Test8 {
    public static void main(String[] args) {
        String str = "你丫abc真de是个fg天才";
        byte[] arr = str.getBytes();
        System.out.println(Arrays.toString(arr));
        System.out.println(str.charAt(0));
        System.out.println(new String(new byte[]{-28, -67, -96}));
    }
}
