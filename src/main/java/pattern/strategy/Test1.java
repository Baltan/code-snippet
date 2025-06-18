package pattern_test.strategy;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 10:52
 */
public class Test1 {
    public static void main(String[] args) {
        Worker w1 = new Worker();
        w1.rewards();

        Worker w2 = new Worker();
        w2.rewards(new CashReward(), new FoodReward(), new ToyReward());
    }
}
