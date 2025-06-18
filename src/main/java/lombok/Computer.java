package lombok;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-01 18:13
 */
@Value
@AllArgsConstructor
public class Computer {
    private String brand;
    private String model;
    private float price;
}
