package random_test;

import java.util.random.RandomGeneratorFactory;

/**
 * Description:
 *
 * @author Baltan
 * @date 2022/5/29 16:10
 */
public class Test1 {
    /**
     * @param args
     * @see <a href="https://mp.weixin.qq.com/s/oqh3xuJRCECWdK8QLJaZ4g">JEP 356：增强的伪随机数生成器</a>
     */
    public static void main(String[] args) {
        RandomGeneratorFactory.all().forEach(factory -> {
            System.out.println(factory.group() + ":" + factory.name());
        });
    }
}
