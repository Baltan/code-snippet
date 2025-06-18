package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-03-27 16:38
 */
public class Test12 {
    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        new Thread(() -> {
            Pipe.SinkChannel sinkChannel = null;

            try {
                /**
                 * 将缓冲区中的数据写进管道
                 */
                sinkChannel = pipe.sink();

                buffer.put("安能摧眉折腰事权贵，使我不得开心颜。".getBytes());
                buffer.flip();

                sinkChannel.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    sinkChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }, "线程-1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Pipe.SourceChannel sourceChannel = null;

            try {
                /**
                 * 读取缓冲区中的数据
                 */
                sourceChannel = pipe.source();

                buffer.flip();
                sourceChannel.read(buffer);

                System.out.println(Thread.currentThread().getName() + " : " + new String(buffer.array(), 0,
                        buffer.limit()));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    sourceChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "线程-2").start();
    }
}
