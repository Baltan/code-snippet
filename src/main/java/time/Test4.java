package time_test;

import java.time.Duration;
import java.time.Instant;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/12 09:27
 */
public class Test4 {
    public static void main(String[] args) {
        Instant start = Instant.now();

        long sum = 0L;
        for (long i = 0; i <= 1000000000; i++) {
            sum += i;
        }
        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println(Duration.between(start, end).toNanos());
    }
}
