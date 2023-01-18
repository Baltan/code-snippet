package concurrent_test;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2023/1/18 17:10
 */
public class CompletableFutureTest1 {
    @SneakyThrows
    public static void main(String[] args) {
        CompletableFuture<Void> cf1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread() + "开始执行第1个异步线程");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread() + "结束执行第1个异步线程");
        });
        CompletableFuture<Integer> cf2 = cf1.thenApplyAsync(x -> {
            System.out.println(Thread.currentThread() + "开始执行第2个异步线程");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread() + "结束执行第2个异步线程");
            return Integer.MAX_VALUE;
        });
        CompletableFuture<Void> cf3 = cf2.thenRunAsync(() -> {
            System.out.println(Thread.currentThread() + "开始执行第3个异步线程");
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("第3个异步线程得到了第2个异步线程的执行结果：" + cf2.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread() + "结束执行第3个异步线程");
        });
        cf3.join();
        System.out.println("主线程得到了第2个异步线程的执行结果：" + cf2.get());
    }
}
