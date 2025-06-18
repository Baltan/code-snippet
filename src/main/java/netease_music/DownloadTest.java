package netease_music;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-11-27 15:35
 */
public class DownloadTest {
    public static void main(String[] args) {
        final String URL = "http://music.163.com/song/media/outer/url?id=";
        final String DIRECTORY_PATH = "/Users/Baltan/Desktop/";
        String musicId = "316277";
        final String SUFFIX = ".mp3";

        String filePath = DownloadUtil.downloadFile(URL, DIRECTORY_PATH, musicId, SUFFIX);
        MusicPlayer mp3 = new MusicPlayer();
        mp3.play(filePath);
    }
}
