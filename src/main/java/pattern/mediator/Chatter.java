package pattern_test.mediator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 15:40
 */
public class Chatter {
    private String name;

    public Chatter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        ChatRoom.deliverMessage(this, message);
    }
}
