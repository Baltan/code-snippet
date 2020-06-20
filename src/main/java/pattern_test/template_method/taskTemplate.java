package pattern_test.template_method;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 13:54
 */
public abstract class taskTemplate {
    public void work() {
        prepare();
        doJob();
        ending();
    }

    protected abstract void prepare();

    protected abstract void doJob();

    protected abstract void ending();
}
