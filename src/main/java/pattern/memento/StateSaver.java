package pattern.memento;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 10:54
 */
public class StateSaver {
    private String state;

    public StateSaver(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "StateSaver{" +
                "state='" + state + '\'' +
                '}';
    }
}
