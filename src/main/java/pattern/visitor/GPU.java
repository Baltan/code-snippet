package pattern.visitor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 10:37
 */
public class GPU implements Component {
    @Override
    public void getPrice(ComputerPriceTable table) {
        table.showPrice(this);
    }
}
