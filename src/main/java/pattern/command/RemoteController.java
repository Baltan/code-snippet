package pattern.command;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 16:43
 */
public class RemoteController {
    private Operation raiseTemperature;
    private Operation dropTemperature;
    private Operation switchMode;

    public RemoteController(Operation raiseTemperature, Operation dropTemperature,
                            Operation switchMode) {
        this.raiseTemperature = raiseTemperature;
        this.dropTemperature = dropTemperature;
        this.switchMode = switchMode;
    }

    public void raiseTemperature() {
        raiseTemperature.operate();
    }

    public void dropTemperature() {
        dropTemperature.operate();
    }

    public void switchMode() {
        switchMode.operate();
    }
}
