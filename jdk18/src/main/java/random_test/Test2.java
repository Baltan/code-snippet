package random_test;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/5/29 16:10
 */
public class Test2 {
    /**
     * @param args
     * @see <a href="https://mp.weixin.qq.com/s/oqh3xuJRCECWdK8QLJaZ4g">JEP 356：增强的伪随机数生成器</a>
     */
    public static void main(String[] args) {
        RandomGeneratorFactory<RandomGenerator> random = RandomGeneratorFactory.of("Random");
        /**
         * 使用时间戳作为随机数种子
         */
        RandomGenerator randomGenerator = random.create(System.currentTimeMillis());

        for (int i = 0; i < 5; i++) {
            System.out.println(randomGenerator.nextInt(10));
        }
    }
}
