package pattern_test.command;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-03 16:27
 */
public class AirConditioner {
    private int temperature;
    private int modeCode;
    private String[] modes;

    public AirConditioner(int temperature, int modeCode) {
        this.temperature = temperature;
        this.modeCode = modeCode;
        modes = new String[]{"通风", "除霜", "制热", "制冷", "自动", "除湿", "睡眠"};
    }

    public void raiseTemperature() {
        temperature++;
    }

    public void dropTemperature() {
        temperature--;
    }

    public void switchMode() {
        modeCode = (modeCode + 1) % modes.length;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getMode() {
        return modes[modeCode];
    }
}
