package word;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author Baltan
 * @date 2020-11-01 15:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelativeInfo {
    private String relationship;
    private String name;
    private String relativeIdCard;
    private String politicsStatus;
    private String work;
}
