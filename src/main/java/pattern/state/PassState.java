package pattern_test.state;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 14:40
 */
public class PassState implements State {
    @Override
    public void action(LightState lightState) {
        System.out.println("绿灯：可以通行");
        lightState.setLight(Light.YELLOW);
        System.out.println("黄灯：减速准备停车");
    }
}
