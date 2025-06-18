package pattern_test.observer.jdk;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 14:46
 */
public class Test1 {
    public static void main(String[] args) {
        Principal principal = new Principal();
        Teacher teacher = new Teacher();
        Student student = new Student();
        Parent parent = new Parent();

        principal.addObserver(teacher);
        principal.addObserver(student);
        principal.addObserver(parent);

        principal.command("\"从今天起推迟一小时放学。\"");

        principal.deleteObserver(parent);

        principal.command("\"距离高考还有一百天。\"");
    }
}
