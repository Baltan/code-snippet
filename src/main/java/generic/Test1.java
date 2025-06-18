package generic;

import java.util.ArrayList;

public class Test1 {

    public static void main(String[] args) {
        Dog d1 = new Dog();
        d1.setName("zs");
        d1.setAge(10);

        Dog d2 = new Dog();
        d2.setName("ls");
        d2.setAge(9);

        ArrayList<Dog> al = new ArrayList<>();
        ArrayList _al = new ArrayList();
        al.add(d1);
        al.add(d2);

        _al.add(d1);
        _al.add(d2);

        Dog d = al.get(1);
        Object o = _al.get(1);

        System.out.println(d);
        System.out.println(o);

        Dog _d = null;

        if (o instanceof Dog) {
            _d = (Dog) o;
        }

        System.out.println(_d.getName());
        System.out.println(_d.getAge());

        // Cat c = (Cat) o;
        // System.out.println(c.getName());// ClassCastException
        // System.out.println(c.getAge());// ClassCastException
    }

}
