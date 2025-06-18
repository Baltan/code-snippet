package pattern.factory_method;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 11:03
 */
public class Test1 {
    public static void main(String[] args) {
        ForeverFactory foreverFactory = new ForeverFactory();
        Bicycle forever = foreverFactory.createBicycle();
        System.out.println(forever.getPrice());

        GiantFactory giantFactory = new GiantFactory();
        Bicycle giant = giantFactory.createBicycle();
        System.out.println(giant.getPrice());

        MeridaFactory meridaFactory = new MeridaFactory();
        Bicycle merida = meridaFactory.createBicycle();
        System.out.println(merida.getPrice());
    }
}
