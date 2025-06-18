package pattern.observer.customize;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 14:24
 */
public class Test1 {
    public static void main(String[] args) {
        Student student = new Student();
        Teacher teacher = new Teacher();
        Parent parent = new Parent();
        Lover lover = new Lover();

        student.addObserver(teacher);
        student.addObserver(parent);
        student.addObserver(lover);

        student.notifyObservers();

        student.removeObserver(lover);

        student.notifyObservers();
    }
}
