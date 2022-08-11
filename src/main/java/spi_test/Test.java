package spi_test;

import java.util.ServiceLoader;

/**
 * Description:
 * 参考：
 * <a href="https://blog.csdn.net/m0_45406092/article/details/109051296"></a>
 * <a href="https://cloud.tencent.com/developer/article/1785056"></a>
 *
 * @author Baltan
 * @date 2022/8/11 22:18
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Subscribe> services = ServiceLoader.load(Subscribe.class);

        for (Subscribe service : services) {
            service.follow();
        }
    }
}
