package pattern_test.chain_of_responsibility;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 15:51
 */
public class GeneralManager extends Leader {
    public GeneralManager(Leader successor) {
        super(successor);
    }

    @Override
    public void handle() {
        if (getSuccessor() == null) {
            System.out.println(this + "批准通过，允许执行");
        } else {
            System.out.println(this + "批准通过，需再请示上级领导：" + getSuccessor());
            getSuccessor().handle();
        }
    }

    @Override
    public String toString() {
        return "GeneralManager";
    }
}
