package pattern_test.interpreter;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 16:58
 */
public class Man {
    private String name;
    private String characteristic;

    public Man(String name, String characteristic) {
        this.name = name;
        this.characteristic = characteristic;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public String getName() {
        return name;
    }
}
