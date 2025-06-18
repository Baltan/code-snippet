package pattern.visitor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 10:40
 */
public class Monitor implements Component {
    @Override
    public void getPrice(ComputerPriceTable table) {
        table.showPrice(this);
    }
}
