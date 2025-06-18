package netease_music;

/**
 * Description: MP3信息实体类
 *
 * @author Baltan
 * @date 2019-11-27 22:51
 */
public class MP3Info {
    /**
     * 歌名
     */
    private String songName;
    /**
     * 歌手
     */
    private String artist;
    /**
     * 专辑
     */
    private String album;
    /**
     * 时长
     */
    private int duration;

    public MP3Info() {
    }

    public MP3Info(String songName, String artist, String album, int duration) {
        this.songName = songName;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MP3Info{" +
                "songName='" + songName + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", duration=" + duration +
                '}';
    }
}
