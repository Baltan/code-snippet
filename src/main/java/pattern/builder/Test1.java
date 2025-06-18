package pattern.builder;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 14:10
 */
public class Test1 {
    public static void main(String[] args) {
        BicycleDirector director = new BicycleDirector();

        BicycleBuilder foreverBuilder = new ForeverBuilder();
        Bicycle forever = director.createBicycle(foreverBuilder);
        System.out.println(forever.getWheel());
        System.out.println(forever.getBrake());
        System.out.println(forever.getFrame());

        System.out.println("----------------");

        BicycleBuilder giantBuilder = new GiantBuilder();
        Bicycle giant = director.createBicycle(giantBuilder);
        System.out.println(giant.getWheel());
        System.out.println(giant.getBrake());
        System.out.println(giant.getFrame());
    }
}
