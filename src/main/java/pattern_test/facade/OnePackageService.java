package pattern_test.facade;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 16:05
 */
public class OnePackageService {
    public void service() {
        new DepartmentA().service();
        new DepartmentB().service();
        new DepartmentC().service();
    }
}
