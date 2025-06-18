package pattern.bridge;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 16:43
 */
public class PrimarySchoolC extends PrimarySchool {
    public PrimarySchoolC(Bridge bridge) {
        super(bridge);
    }

    @Override
    void graduateFrom() {
        System.out.println("从PrimarySchoolC毕业");
        bridge.goToSchool();
    }
}
