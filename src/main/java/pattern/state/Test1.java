package pattern_test.state;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 14:43
 */
public class Test1 {
    public static void main(String[] args) {
        LightState lightState = new LightState(Light.GREEN);

        System.out.println(lightState.getLight());

        PassState passState = new PassState();
        passState.action(lightState);
        System.out.println(lightState.getLight());

        System.out.println("-----------------------");

        ReadyToStopState readyToStopState = new ReadyToStopState();
        readyToStopState.action(lightState);
        System.out.println(lightState.getLight());

        System.out.println("-----------------------");

        StopState stopState = new StopState();
        stopState.action(lightState);
        System.out.println(lightState.getLight());
    }
}
