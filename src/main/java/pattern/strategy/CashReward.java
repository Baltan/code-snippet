package pattern_test.strategy;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 10:49
 */
public class CashReward implements RewardStrategy {
    @Override
    public void reward() {
        System.out.println("奖励人民币100元");
    }
}
