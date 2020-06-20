package pattern_test.bridge;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 16:43
 */
public class Test1 {
    public static void main(String[] args) {
        MiddleSchoolB middleSchoolB = new MiddleSchoolB();
        PrimarySchoolA primarySchoolA = new PrimarySchoolA(middleSchoolB);
        primarySchoolA.graduateFrom();
    }
}
