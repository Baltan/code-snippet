package pattern.template_method;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 14:02
 */
public class Test1 {
    public static void main(String[] args) {
        Programmer programmer = new Programmer();
        programmer.work();

        Doctor doctor = new Doctor();
        doctor.work();
    }
}
