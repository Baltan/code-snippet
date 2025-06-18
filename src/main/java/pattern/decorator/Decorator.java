package pattern.decorator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 15:05
 */
public class Decorator implements Player {
    private Player player;

    public Decorator(Player player) {
        this.player = player;
    }

    @Override
    public void play() {
        player.play();
    }
}
