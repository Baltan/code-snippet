package apache_commons_test.math;

import org.apache.commons.math3.stat.descriptive.moment.*;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.apache.commons.math3.stat.descriptive.summary.SumOfSquares;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/9/26 15:46
 */
public class Test1 {
    public static void main(String[] args) {
        double[] array = {1.2, 3.56, 5.34, 7.23, 9.43, 0.55};
        Min min = new Min();
        Max max = new Max();
        Mean mean = new Mean(); // 算术平均数
        GeometricMean geoMean = new GeometricMean(); // 几何平均数
        Product product = new Product();
        Sum sum = new Sum();
        Variance variance = new Variance(); // 方差
        StandardDeviation StandardDeviation = new StandardDeviation(); // 标准差
        SumOfSquares sumOfSquares = new SumOfSquares(); // 平方和
        Kurtosis kurtosis = new Kurtosis(); // 峰度
        Skewness skewness = new Skewness(); // 偏度系数

        System.out.println("最小值：" + min.evaluate(array));
        System.out.println("最大值：" + max.evaluate(array));
        System.out.println("算数平均数：" + mean.evaluate(array));
        System.out.println("几何平均数：" + geoMean.evaluate(array));
        System.out.println("乘积：" + product.evaluate(array));
        System.out.println("总和：" + sum.evaluate(array));
        System.out.println("方差：" + variance.evaluate(array));
        System.out.println("标准差：" + StandardDeviation.evaluate(array));
        System.out.println("平方和：" + sumOfSquares.evaluate(array));
        System.out.println("峰度：" + kurtosis.evaluate(array));
        System.out.println("偏度系数：" + skewness.evaluate(array));
    }
}
