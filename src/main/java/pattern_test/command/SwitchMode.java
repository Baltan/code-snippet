package pattern_test.command;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 16:30
 */
public class SwitchMode implements Operation {
    private AirConditioner airConditioner;

    public SwitchMode(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void operate() {
        airConditioner.switchMode();
    }
}
