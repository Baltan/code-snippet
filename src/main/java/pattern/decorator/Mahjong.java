package pattern.decorator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 15:11
 */
public class Mahjong extends Decorator {
    public Mahjong(Player player) {
        super(player);
    }

    @Override
    public void play() {
        super.play();
        playMahjong();
    }

    private void playMahjong() {
        System.out.println("打麻将");
    }
}
