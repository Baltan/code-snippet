package pattern.chain_of_responsibility;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 15:46
 */
public abstract class Leader {
    protected Leader successor;

    public Leader(Leader successor) {
        this.successor = successor;
    }

    public abstract void handle();

    public Leader getSuccessor() {
        return successor;
    }
}
