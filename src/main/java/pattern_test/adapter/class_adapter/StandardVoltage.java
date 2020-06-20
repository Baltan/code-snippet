package pattern_test.adapter.class_adapter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 14:42
 */
public class StandardVoltage implements Voltage {
    @Override
    public void standardOutput() {
        System.out.println("电压：220V");
    }
}
