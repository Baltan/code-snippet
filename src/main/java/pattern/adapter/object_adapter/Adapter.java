package pattern_test.adapter.object_adapter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 14:58
 */
public class Adapter implements Voltage {
    private AdapteeVoltage adapteeVoltage;

    public Adapter(AdapteeVoltage adapteeVoltage) {
        this.adapteeVoltage = adapteeVoltage;
    }

    @Override
    public void standardOutput() {
        adapteeVoltage.output();
    }
}
