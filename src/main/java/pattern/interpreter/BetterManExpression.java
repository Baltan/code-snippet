package pattern.interpreter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 16:41
 */
public class BetterManExpression implements Expression {
    private Expression tallExpression;
    private Expression richExpression;
    private Expression handsomeExpression;

    public BetterManExpression(Expression tallExpression, Expression richExpression,
                               Expression handsomeExpression) {
        this.tallExpression = tallExpression;
        this.richExpression = richExpression;
        this.handsomeExpression = handsomeExpression;
    }

    @Override
    public boolean interpret(String description) {
        return (tallExpression.interpret(description) && richExpression.interpret(description)) ||
                (tallExpression.interpret(description) && handsomeExpression.interpret(description)) ||
                (richExpression.interpret(description) && handsomeExpression.interpret(description));
    }
}
