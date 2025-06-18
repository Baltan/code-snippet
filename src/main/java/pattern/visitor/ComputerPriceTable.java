package pattern.visitor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 15:17
 */
public class ComputerPriceTable implements PriceTable {
    @Override
    public void showPrice(CPU cpu) {
        System.out.println("cpu的价格是： 2000元");
    }

    @Override
    public void showPrice(GPU gpu) {
        System.out.println("gpu的价格是： 1500元");
    }

    @Override
    public void showPrice(HardDisk hardDisk) {
        System.out.println("hardDisk的价格是： 1000元");
    }

    @Override
    public void showPrice(Keyboard keyboard) {
        System.out.println("keyboard的价格是： 500元");
    }

    @Override
    public void showPrice(Memory memory) {
        System.out.println("memory的价格是： 200元");
    }

    @Override
    public void showPrice(Monitor monitor) {
        System.out.println("monitor的价格是： 1500元");
    }

    @Override
    public void showPrice(Mouse mouse) {
        System.out.println("mouse的价格是： 200元");
    }
}
