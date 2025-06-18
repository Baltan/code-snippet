package lombok;

import lombok.Builder;
import lombok.ToString;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-01 18:19
 */
@Builder
@ToString
public class Desk {
    private float length;
    private float width;
    private float height;
    private String color;
}
