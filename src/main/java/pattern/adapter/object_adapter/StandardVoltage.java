package pattern.adapter.object_adapter;

import pattern.adapter.class_adapter.Voltage;

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
