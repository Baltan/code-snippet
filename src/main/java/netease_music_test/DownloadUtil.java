package netease_music_test;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.ID3v23Frame;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-11-27 15:13
 */
public class DownloadUtil {
    private DownloadUtil() {
    }

    /**
     * 下载文件
     *
     * @param urlPath
     * @param directoryPath 下载目标文件夹
     * @param musicId       音乐id
     * @param suffix        文件后缀（.mp3）
     * @return 重命名后的文件名（含文件路径）
     * <p>
     * 参考：<a href="https://blog.csdn.net/qq_36577699/article/details/82115380"></a>
     */
    public static String downloadFile(String urlPath, String directoryPath, String musicId, String suffix) {
        final String REQUEST_METHOD = "POST";
        final String REQUEST_PROPERTY_CHARSET = "charset";
        final String CHARSET_UTF8 = StandardCharsets.UTF_8.name();
        String filename = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;

        try {
            /**
             * 统一资源定位符
             */
            URL url = new URL(urlPath + musicId);
            /**
             * HTTP连接类
             */
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            /**
             * 配置请求的方法，默认是GET请求
             */
            httpURLConnection.setRequestMethod(REQUEST_METHOD);
            /**
             * 配置字符编码
             */
            httpURLConnection.setRequestProperty(REQUEST_PROPERTY_CHARSET, CHARSET_UTF8);
            /**
             * 打开到此URL资源的通信连接
             */
            httpURLConnection.connect();
            /**
             * 例如：/song/media/outer/url?id=316277
             */
//            String filePathUrl = httpURLConnection.getURL().getFile();
            /**
             * 文件大小
             */
//            int fileSize = httpURLConnection.getContentLength();

            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
            }
            /**
             * 例如："/Users/Baltan/Desktop/"+"316277"+".mp3"
             */
            filename = directoryPath + musicId + suffix;
            File file = new File(filename);
            bis = new BufferedInputStream(httpURLConnection.getInputStream());
            fos = new FileOutputStream(file);
            int size;
            byte[] buf = new byte[1024];

            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }

            MP3Info mp3Info = getMP3Info(file);
            filename = rename(file, directoryPath, suffix, mp3Info);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filename;
    }

    /**
     * 获取MP3文件信息
     *
     * @param sourceFile MP3源文件
     * @return MP3信息实体类
     * <p>
     * 参考：<a href="https://blog.csdn.net/qq_41890526/article/details/85397352"></a>
     */
    public static MP3Info getMP3Info(File sourceFile) {
        MP3Info mp3Info = null;

        try {
            MP3File mp3File = (MP3File) AudioFileIO.read(sourceFile);
            /**
             * MP3头信息
             */
            MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
            /**
             * 歌名
             */
            ID3v23Frame songnameFrame = (ID3v23Frame) mp3File.getID3v2Tag().frameMap.get("TIT2");
            String songName = songnameFrame.getContent();
            /**
             * 歌手
             */
            ID3v23Frame artistFrame = (ID3v23Frame) mp3File.getID3v2Tag().frameMap.get("TPE1");
            String artist = artistFrame.getContent();
            /**
             * 专辑
             */
            ID3v23Frame albumFrame = (ID3v23Frame) mp3File.getID3v2Tag().frameMap.get("TALB");
            String album = albumFrame.getContent();
            /**
             * 时长
             */
            int duration = audioHeader.getTrackLength();

            mp3Info = new MP3Info(songName, artist, album, duration);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        return mp3Info;
    }

    /**
     * 重命名文件
     *
     * @param sourceFile    重命名前的文件
     * @param directoryPath 目标文件夹
     * @param suffix        文件后缀（.mp3）
     * @param mp3Info       MP3信息实体类
     * @return 重命名后的文件名（含文件路径）
     */
    public static String rename(File sourceFile, String directoryPath, String suffix, MP3Info mp3Info) {
        /**
         * 重命名后的文件名
         */
        String filename = mp3Info.getArtist() + " - " + mp3Info.getSongName();
        String filePath = directoryPath + filename + suffix;
        File targetFile = new File(filePath);
        /**
         * 重命名文件
         */
        sourceFile.renameTo(targetFile);
        return filePath;
    }

    /**
     * 重命名文件
     *
     * @param sourceFile    重命名前的文件
     * @param directoryPath 目标文件夹
     * @param suffix        文件后缀（.mp3）
     * @return 重命名后的文件名（含文件路径）
     * <p>
     * 参考：<a href="https://jingyan.baidu.com/article/03b2f78c4d5eae5ea237aee7.html"></a>
     */
    @Deprecated
    public static String rename(File sourceFile, String directoryPath, String suffix) {
        RandomAccessFile raf = null;
        String filePath = null;
        final int OFFSET = 128;
        final String MODE_READ = "r";

        try {
            /**
             * 初始化标签信息的byte数组
             */
            byte[] buf = new byte[OFFSET];
            /**
             * 随机读写方式打开MP3文件
             */
            raf = new RandomAccessFile(sourceFile, MODE_READ);
            /**
             * 移动到MP3文件末尾
             */
            raf.seek(raf.length() - OFFSET);
            /**
             * 读取标签信息
             */
            raf.read(buf);

            if (buf.length != OFFSET) {
                throw new Exception("MP3标签信息数据长度不合法!");
            }

            if (!"TAG".equalsIgnoreCase(new String(buf, 0, 3))) {
                throw new Exception("MP3标签信息数据格式不正确!");
            }
            /**
             * 歌曲名称
             */
            String songName = new String(buf, 3, 30, "utf-8").trim();
            /**
             * 歌手名字
             */
            String artist = new String(buf, 33, 30, "utf-8").trim();
            /**
             * 专辑名称
             */
//            String album = new String(buf, 63, 30, "utf-8").trim();
            /**
             * 出品年份
             */
//            String year = new String(buf, 93, 4, "utf-8").trim();
            /**
             * 备注信息
             */
//            String comment = new String(buf, 97, 28, "utf-8").trim();
            /**
             * 重命名后的文件名
             */
            String filename = artist + " - " + songName;
            filePath = directoryPath + filename + suffix;
            File targetFile = new File(filePath);
            /**
             * 重命名文件
             */
            sourceFile.renameTo(targetFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }
}
