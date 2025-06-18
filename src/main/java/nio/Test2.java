package nio;

import java.io.RandomAccessFile;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/6/9 22:40
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile sourceFile = new RandomAccessFile("/Users/Baltan/Desktop/profile.txt", "rw");
        FileChannel sourceChannel = sourceFile.getChannel();

        RandomAccessFile destinationSource = new RandomAccessFile("/Users/Baltan/Desktop/profile_copy.txt", "rw");
        FileChannel destinationChannel = destinationSource.getChannel();

//        RandomAccessFile destinationSource2 = new RandomAccessFile("/Users/Baltan/Desktop/profile_copy2.txt", "rw");
//        FileChannel destinationChannel2 = destinationSource.getChannel();

        long position = 0;
        long amount = sourceChannel.size();
        destinationChannel.transferFrom(sourceChannel, position, amount);

//        sourceChannel.transferTo(position, amount, destinationChannel2);
    }
}
