package pattern_test.bridge;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 16:42
 */
public class PrimarySchoolB extends PrimarySchool {
    public PrimarySchoolB(Bridge bridge) {
        super(bridge);
    }

    @Override
    void graduateFrom() {
        System.out.println("从PrimarySchoolB毕业");
        bridge.goToSchool();
    }
}
