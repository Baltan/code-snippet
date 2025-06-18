package pattern_test.mediator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 15:44
 */
public class Test1 {
    private Chatter a;

    public static void main(String[] args) {
        Chatter a = new Chatter("A");
        Chatter b = new Chatter("B");
        Chatter c = new Chatter("C");

        a.sendMessage("我是A");
        b.sendMessage("我是B");
        c.sendMessage("我是C");
    }
}
