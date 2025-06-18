package pattern.state;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 14:37
 */
public interface State {
    void action(LightState lights);
}
