package pattern.memento;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 10:59
 */
public class StateManager {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public StateSaver saveState(String state) {
        return new StateSaver(state);
    }

    public void restoreState(StateSaver stateSaver) {
        state = stateSaver.getState();
    }
}
