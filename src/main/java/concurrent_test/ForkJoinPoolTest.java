package concurrent_test;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/7/11 22:56
 */
public class ForkJoinPoolTest {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(0L, 1000000000L);
        Long result = pool.invoke(task);
        System.out.println(result);
    }
}

// RecursiveAction无返回值，RecursiveTask有返回值
class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private static final long serialVersionUID = 3879982956567306531L;

    private long start;
    private long end;
    private static final long THRESHOLD = 10000L; // 临界值

    public ForkJoinSumCalculator(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        long length = end - start;

        if (length > THRESHOLD) {
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;

            ForkJoinSumCalculator left = new ForkJoinSumCalculator(start, middle);
            left.fork(); // 拆分任务并压入线程队列

            ForkJoinSumCalculator right = new ForkJoinSumCalculator(middle + 1, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}