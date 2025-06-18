package pattern.visitor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-04 15:15
 */
public interface PriceTable {
    void showPrice(CPU cpu);

    void showPrice(GPU gpu);

    void showPrice(HardDisk hardDisk);

    void showPrice(Keyboard keyboard);

    void showPrice(Memory memory);

    void showPrice(Monitor monitor);

    void showPrice(Mouse mouse);
}
