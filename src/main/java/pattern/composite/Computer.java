package pattern.composite;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-04-02 10:45
 */
public class Computer extends Composite {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.add(new CPU());
        computer.add(new GPU());
        computer.add(new HardDisk());
        computer.add(new Keyboard());
        computer.add(new Memory());
        computer.add(new Monitor());
        computer.add(new Mouse());
        System.out.println(computer.getPrice());
    }
}
