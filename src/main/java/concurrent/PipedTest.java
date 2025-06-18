package concurrent_test;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.TimeUnit;

/**
 * Description: 管道通信
 *
 * @author Baltan
 * @date 2019/1/9 19:42
 */
public class PipedTest {
    public static void main(String[] args) throws IOException {
        PipedWriter writer = new PipedWriter();
        PipedReader reader = new PipedReader();

        writer.connect(reader);

        Thread t1 = new Thread(() -> {
            System.out.println("线程1启动");
            try {
                for (int i = 0; i < 10; i++) {
                    writer.write(i + "");
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
            } catch (Exception e) {
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread t2 = new Thread(() -> {
            System.out.println("线程2启动");
            int msg;
            try {
                while ((msg = reader.read()) != -1) {
                    System.out.println(String.valueOf((char) msg));
                }
            } catch (Exception e) {
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
