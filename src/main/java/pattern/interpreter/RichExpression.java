package pattern.interpreter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 16:36
 */
public class RichExpression implements Expression {
    private String characteristic;

    public RichExpression(String characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public boolean interpret(String description) {
        return description.contains(characteristic);
    }
}
