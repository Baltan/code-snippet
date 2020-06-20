package netease_music_test;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-11-27 15:07
 */
public class MusicPlayer {
    private Player player;

    /**
     * 开始播放
     *
     * @param filePath
     */
    public void play(String filePath) {
        BufferedInputStream bis = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(filePath));
            player = new Player(bis);
            player.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } finally {
            stop();

            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 停止播放
     */
    public void stop() {
        player.close();
    }
}
