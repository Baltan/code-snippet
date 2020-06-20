package pattern_test.command;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 16:46
 */
public class Test1 {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner(25, 0);
        RaiseTemperature raiseTemperature = new RaiseTemperature(airConditioner);
        DropTemperature dropTemperature = new DropTemperature(airConditioner);
        SwitchMode switchMode = new SwitchMode(airConditioner);
        RemoteController remoteController =
                new RemoteController(raiseTemperature, dropTemperature, switchMode);

        System.out.println(airConditioner.getTemperature());
        System.out.println(airConditioner.getMode());

        remoteController.raiseTemperature();
        remoteController.raiseTemperature();
        remoteController.switchMode();
        remoteController.switchMode();
        remoteController.switchMode();

        System.out.println(airConditioner.getTemperature());
        System.out.println(airConditioner.getMode());
    }
}
