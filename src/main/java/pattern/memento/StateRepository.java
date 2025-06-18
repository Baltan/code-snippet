package pattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 10:55
 */
public class StateRepository {
    private List<StateSaver> list = new ArrayList<>();

    public void saveState(StateSaver stateSaver) {
        list.add(stateSaver);
    }

    public StateSaver getState(int index) {
        return list.get(index);
    }

    public List<StateSaver> getStates() {
        return list;
    }
}
