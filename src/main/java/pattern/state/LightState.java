package pattern_test.state;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 14:38
 */
public class LightState {
    private Light light;

    public LightState(Light light) {
        this.light = light;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }
}
