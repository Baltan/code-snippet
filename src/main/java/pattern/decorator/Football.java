package pattern.decorator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 15:06
 */
public class Football extends Decorator {
    public Football(Player player) {
        super(player);
    }

    @Override
    public void play() {
        super.play();
        playFootball();
    }

    private void playFootball() {
        System.out.println("踢球");
    }
}
