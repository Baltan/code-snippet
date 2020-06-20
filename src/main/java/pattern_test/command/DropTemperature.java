package pattern_test.command;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 16:29
 */
public class DropTemperature implements Operation {
    private AirConditioner airConditioner;

    public DropTemperature(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public void operate() {
        airConditioner.dropTemperature();
    }
}
