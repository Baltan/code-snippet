package pattern.strategy;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 10:51
 */
public class ToyReward implements RewardStrategy {
    @Override
    public void reward() {
        System.out.println("奖励女朋友一个");
    }
}
