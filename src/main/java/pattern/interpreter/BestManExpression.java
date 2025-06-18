package pattern_test.interpreter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 16:44
 */
public class BestManExpression implements Expression {
    private Expression tallExpression;
    private Expression richExpression;
    private Expression handsomeExpression;

    public BestManExpression(Expression tallExpression, Expression richExpression,
                             Expression handsomeExpression) {
        this.tallExpression = tallExpression;
        this.richExpression = richExpression;
        this.handsomeExpression = handsomeExpression;
    }

    @Override
    public boolean interpret(String description) {
        return tallExpression.interpret(description) && richExpression.interpret(description) &&
                handsomeExpression.interpret(description);
    }
}
