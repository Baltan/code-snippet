package pattern_test.decorator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 15:15
 */
public class NaughtyMan implements Player {
    @Override
    public void play() {
        System.out.println("玩啥呢");
    }
}
