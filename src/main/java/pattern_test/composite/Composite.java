package pattern_test.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 10:42
 */
public abstract class Composite implements Component {
    private List<Component> list = new ArrayList<>();

    public void add(Component component) {
        list.add(component);
    }

    @Override
    public int getPrice() {
        return list.stream().map(Component::getPrice).reduce(0, (x, y) -> x + y);
    }
}
