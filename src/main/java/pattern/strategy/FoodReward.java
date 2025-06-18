package pattern.strategy;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 10:50
 */
public class FoodReward implements RewardStrategy {
    @Override
    public void reward() {
        System.out.println("奖励蛋挞一盒");
    }
}
