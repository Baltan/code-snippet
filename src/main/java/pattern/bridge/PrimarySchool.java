package pattern.bridge;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 16:40
 */
public abstract class PrimarySchool {
    protected Bridge bridge;

    public PrimarySchool(Bridge bridge) {
        this.bridge = bridge;
    }

    abstract void graduateFrom();

    public Bridge getBridge() {
        return bridge;
    }
}
