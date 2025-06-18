package pattern_test.visitor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 15:30
 */
public class Test1 {
    public static void main(String[] args) {
        ComputerPriceTable table = new ComputerPriceTable();
        Computer computer = new Computer();
        computer.getPrice(table);
    }
}
