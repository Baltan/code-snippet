package pattern.template_method;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 14:00
 */
public class Programmer extends taskTemplate {
    @Override
    protected void prepare() {
        System.out.println("改昨天的bug");
    }

    @Override
    protected void doJob() {
        System.out.println("开发新需求");
    }

    @Override
    protected void ending() {
        System.out.println("提交代码");
    }
}
