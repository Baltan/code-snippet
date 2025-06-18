package pattern_test.bridge;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 16:38
 */
public class MiddleSchoolA implements Bridge {
    @Override
    public void goToSchool() {
        System.out.println("入学MiddleSchoolA");
    }
}
