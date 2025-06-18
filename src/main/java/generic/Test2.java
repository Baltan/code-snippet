package generic;

public class Test2 {
    public static void main(String[] args) {
        Container<String> c1 = new Container<>();
        c1.setData("666");

        Container<Integer> c2 = new Container<>();
        c2.setData(666);

        System.out.println(c1.getData().getClass());
        System.out.println(c2.getData().getClass());
        System.out.println(c1.getClass() == c2.getClass());
    }
}
