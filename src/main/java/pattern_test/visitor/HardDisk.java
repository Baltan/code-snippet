package pattern_test.visitor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 10:39
 */
public class HardDisk implements Component {
    @Override
    public void getPrice(ComputerPriceTable table) {
        table.showPrice(this);
    }
}
