package pattern.strategy;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 10:52
 */
public class Worker {
    public void rewards(RewardStrategy... rewards) {
        if (rewards == null || rewards.length == 0) {
            System.out.println("滚犊子，还想要奖励！？");
        } else {
            System.out.println("获得以下奖励：");
            for (RewardStrategy reward : rewards) {
                reward.reward();
            }
        }
    }
}
