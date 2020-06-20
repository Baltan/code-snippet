package pattern_test.interpreter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 16:37
 */
public class HandsomeExpression implements Expression {
    private String characteristic;

    public HandsomeExpression(String characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public boolean interpret(String description) {
        return description.contains(characteristic);
    }
}
