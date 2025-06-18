package pattern_test.chain_of_responsibility;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 15:54
 */
public class Test1 {
    public static void main(String[] args) {
        CEO ceo = new CEO(null);
        GeneralManager generalManager = new GeneralManager(ceo);
        Director director = new Director(generalManager);

        director.handle();
    }
}
