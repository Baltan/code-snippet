package pattern_test.command;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 16:27
 */
public class RaiseTemperature implements Operation {
    private AirConditioner airConditioner;

    public RaiseTemperature(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void operate() {
        airConditioner.raiseTemperature();
    }
}
