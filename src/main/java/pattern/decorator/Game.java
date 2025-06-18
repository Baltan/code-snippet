package pattern.decorator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 15:09
 */
public class Game extends Decorator {
    public Game(Player player) {
        super(player);
    }

    @Override
    public void play() {
        super.play();
        playGame();
    }

    private void playGame() {
        System.out.println("打游戏");
    }
}
