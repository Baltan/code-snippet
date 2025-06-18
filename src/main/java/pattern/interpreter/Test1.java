package pattern.interpreter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 16:57
 */
public class Test1 {
    public static void main(String[] args) {
        TallExpression tallExpression = new TallExpression("高");
        RichExpression richExpression = new RichExpression("富");
        HandsomeExpression handsomeExpression = new HandsomeExpression("帅");

        Man wlh = new Man("Wang LiHong", "高富帅");
        System.out.println(wlh.getName() + "是GoodMan？ --- " +
                new GoodManExpression(tallExpression, richExpression, handsomeExpression)
                        .interpret(wlh.getCharacteristic()));

        System.out.println(wlh.getName() + "是BetterMan？ --- " +
                new BetterManExpression(tallExpression, richExpression, handsomeExpression)
                        .interpret(wlh.getCharacteristic()));

        System.out.println(wlh.getName() + "是BestMan？ --- " +
                new BestManExpression(tallExpression, richExpression, handsomeExpression)
                        .interpret(wlh.getCharacteristic()));
    }
}
