package pattern_test.decorator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 15:12
 */
public class Test1 {
    public static void main(String[] args) {
        Player player = new Mahjong(new Game(new Football(new NaughtyMan())));
        player.play();
    }
}
