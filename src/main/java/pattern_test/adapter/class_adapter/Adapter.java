package pattern_test.adapter.class_adapter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 14:45
 */
public class Adapter extends AdapteeVoltage implements Voltage {
    @Override
    public void standardOutput() {
        super.output();
    }
}
