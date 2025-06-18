package pattern.visitor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 15:20
 */
public class Computer implements Component {
    private Component[] components;

    public Computer() {
        this.components = new Component[]{new CPU(), new GPU(), new HardDisk(), new Keyboard(), new Memory(),
                new Monitor(), new Mouse()};
    }

    @Override
    public void getPrice(ComputerPriceTable table) {
        for (Component component : components) {
            component.getPrice(table);
        }
    }
}
