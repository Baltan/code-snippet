package postman_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/11/30 14:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfo {
    private String serviceCode;
    private String name;
    private String id;
}
