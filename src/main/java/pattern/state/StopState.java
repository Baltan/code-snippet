package pattern.state;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 14:38
 */
public class StopState implements State {
    @Override
    public void action(LightState lightState) {
        System.out.println("红灯：停车等候");
        lightState.setLight(Light.GREEN);
        System.out.println("绿灯：准备通行");
    }
}
