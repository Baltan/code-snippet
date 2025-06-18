package pattern_test.mediator;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 15:41
 */
public class ChatRoom {
    public static void deliverMessage(Chatter chatter, String message) {
        System.out.println(chatter.getName() + "说： " + message);
    }
}
