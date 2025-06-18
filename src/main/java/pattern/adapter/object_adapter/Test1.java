package pattern.adapter.object_adapter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 14:59
 */
public class Test1 {
    public static void main(String[] args) {
        StandardVoltage standardVoltage = new StandardVoltage();
        standardVoltage.standardOutput();

        Adapter adapter = new Adapter(new AdapteeVoltage());
        adapter.standardOutput();
    }
}
