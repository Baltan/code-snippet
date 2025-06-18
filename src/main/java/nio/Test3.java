package nio_test;

import java.nio.ByteBuffer;

/**
 * Description: 缓冲区基本操作
 *
 * @author Baltan
 * @date 2019-03-20 15:04
 */
public class Test3 {
    public static void main(String[] args) {
        /**
         * 非直接缓冲区，分配的缓冲区在JVM中
         */
        ByteBuffer buf = ByteBuffer.allocate(1024);
        /**
         * 直接缓冲区，分配的缓冲区在操作系统的物理内存中
         */
//        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

        /**
         * 判断是否是直接缓冲区
         */
        System.out.println(buf.isDirect());
        System.out.println("-----------------------");

        System.out.println("capacity: " + buf.capacity());
        System.out.println("limit:    " + buf.limit());
        System.out.println("position: " + buf.position());
        System.out.println("-----------------------");

//        String str = "安能摧眉折腰事权贵，使我不得开心颜。";
        String str = "just do it tomorrow.";
        buf.put(str.getBytes());

        System.out.println("capacity: " + buf.capacity());
        System.out.println("limit:    " + buf.limit());
        System.out.println("position: " + buf.position());
        System.out.println("-----------------------");

        /**
         * 从写模式切换到读模式
         */
        buf.flip();

        System.out.println("capacity: " + buf.capacity());
        System.out.println("limit:    " + buf.limit());
        System.out.println("position: " + buf.position());
        System.out.println("-----------------------");

        byte[] b = new byte[buf.limit()];
        buf.get(b);
        System.out.println(new String(b));

        System.out.println("capacity: " + buf.capacity());
        System.out.println("limit:    " + buf.limit());
        System.out.println("position: " + buf.position());
        System.out.println("-----------------------");

        /**
         * 可以重读数据
         */
        buf.rewind();

        System.out.println("capacity: " + buf.capacity());
        System.out.println("limit:    " + buf.limit());
        System.out.println("position: " + buf.position());
        System.out.println("-----------------------");

        /**
         * 清空缓冲区，但是缓冲区中数据依然存在，但是出于"被遗忘"状态
         */
        buf.clear();

        System.out.println("capacity: " + buf.capacity());
        System.out.println("limit:    " + buf.limit());
        System.out.println("position: " + buf.position());
        System.out.println((char) buf.get());
        System.out.println((char) buf.get());

        /**
         * 标记当前position
         */
        buf.mark();
        System.out.println("position: " + buf.position());

        System.out.println((char) buf.get());
        System.out.println((char) buf.get());

        System.out.println("position: " + buf.position());
        /**
         * 恢复到上一次mark过的position
         */
        buf.reset();
        System.out.println("position: " + buf.position());

        System.out.println("-----------------------");

        /**
         * 如果缓冲区中还有空间，获得剩余空间的大小
         */
        if (buf.hasRemaining()) {
            System.out.println("remaining: " + buf.remaining());
        }
    }
}
