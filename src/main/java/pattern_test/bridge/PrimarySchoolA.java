package pattern_test.bridge;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 16:42
 */
public class PrimarySchoolA extends PrimarySchool {
    public PrimarySchoolA(Bridge bridge) {
        super(bridge);
    }

    @Override
    void graduateFrom() {
        System.out.println("从PrimarySchoolA毕业");
        bridge.goToSchool();
    }
}
