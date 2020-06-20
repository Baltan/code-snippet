package lombok_test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-12-01 18:07
 */
@Data
@AllArgsConstructor
public class Book {
    private String name;
    private String author;
}
