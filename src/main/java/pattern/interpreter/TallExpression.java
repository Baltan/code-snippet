package pattern.interpreter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 16:09
 */
public class TallExpression implements Expression {
    private String characteristic;

    public TallExpression(String characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public boolean interpret(String description) {
        return description.contains(characteristic);
    }
}
