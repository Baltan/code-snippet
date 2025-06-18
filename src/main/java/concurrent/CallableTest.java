package concurrent;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/18 16:19
 */
public class CallableTest implements Callable<Double> {

    @Override
    public Double call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return Math.random();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            FutureTask<Double> task = new FutureTask<>(new CallableTest());
            new Thread(task).start();
            System.out.println(task.get());
        }
    }
}
