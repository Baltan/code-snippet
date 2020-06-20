package algorithm_test;

/**
 * Description: 汉诺塔
 *
 * @author Baltan
 * @date 2019-04-19 16:54
 */
public class HanoiTower {
    public static void main(String[] args) {
        play(1, 'a', 'b', 'c');
        System.out.println("---------------------");
        play(2, 'a', 'b', 'c');
        System.out.println("---------------------");
        play(3, 'a', 'b', 'c');
    }

    public static void play(int num, char startPole, char middlePole, char endPole) {
        if (num == 1) {
            System.out.printf("%s ---> %s", startPole, endPole);
            System.out.println();
        } else {
            play(num - 1, startPole, endPole, middlePole);
            System.out.printf("%s ---> %s", startPole, endPole);
            System.out.println();
            play(num - 1, middlePole, startPole, endPole);
        }
    }
}
