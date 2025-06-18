package pattern_test.template_method;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 14:01
 */
public class Doctor extends taskTemplate {
    @Override
    protected void prepare() {
        System.out.println("换上大白袍");
    }

    @Override
    protected void doJob() {
        System.out.println("查房、坐诊");
    }

    @Override
    protected void ending() {
        System.out.println("和值班医生交接");
    }
}
