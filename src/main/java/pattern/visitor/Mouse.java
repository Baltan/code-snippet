package pattern.visitor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 10:38
 */
public class Mouse implements Component {
    @Override
    public void getPrice(ComputerPriceTable table) {
        table.showPrice(this);
    }
}
