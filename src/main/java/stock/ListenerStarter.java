package stock;

/**
 * Description:
 *
 * @author baltan
 * @date 2025/6/18 17:31
 */
public class ListenerStarter {
    public static void main(String[] args) {
        ClsTelegraphListener.start();
        HongKongStockIntradayConditionListener.start();
    }
}
