package stream;


import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/28 18:17
 */
public class Test7 {
    public static void main(String[] args) {
        Instant start1 = Instant.now();

        long sum1 = LongStream.rangeClosed(0, 1000000000L).parallel().sum();
        System.out.println(sum1);

        Instant end1 = Instant.now();
        System.out.println("并行流用时：" + Duration.between(start1, end1).toMillis() + "毫秒");

        System.out.println("-----------------------------------");

        Instant start2 = Instant.now();

        long sum2 = LongStream.rangeClosed(0, 1000000000L).sum();
        System.out.println(sum2);

        Instant end2 = Instant.now();
        System.out.println("串行流用时：" + Duration.between(start2, end2).toMillis() + "毫秒");

        System.out.println("-----------------------------------");

        Instant start3 = Instant.now();

        long sum3 = LongStream.rangeClosed(0, 1000000000L).parallel().reduce(0, Long::sum);
        System.out.println(sum3);

        Instant end3 = Instant.now();
        System.out.println("并行流Reduce用时：" + Duration.between(start3, end3).toMillis() + "毫秒");

        System.out.println("-----------------------------------");

        Instant start4 = Instant.now();

        long sum4 = LongStream.rangeClosed(0, 1000000000L).parallel().reduce(0, Long::sum);
        System.out.println(sum4);

        Instant end4 = Instant.now();
        System.out.println("串行流Reduce用时：" + Duration.between(start4, end4).toMillis() + "毫秒");
    }
}
