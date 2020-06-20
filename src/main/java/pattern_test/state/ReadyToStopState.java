package pattern_test.state;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 14:41
 */
public class ReadyToStopState implements State {
    @Override
    public void action(LightState lightState) {
        System.out.println("黄灯：减速准备停车");
        lightState.setLight(Light.RED);
        System.out.println("红灯：停车等候");
    }
}
